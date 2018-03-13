package com.visdan.hotel.account.config;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariable;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableLifecycle;
import com.netflix.hystrix.strategy.properties.HystrixProperty;

public class HystrixRequestScopeConcurrencyStrategy extends HystrixConcurrencyStrategy {
	private HystrixConcurrencyStrategy existingStrategy;

	public HystrixRequestScopeConcurrencyStrategy(HystrixConcurrencyStrategy existingStrategy) {
		this.existingStrategy = existingStrategy;
	}

	@Override
	public BlockingQueue<Runnable> getBlockingQueue(int maxQueueSize) {
		return existingStrategy != null ? existingStrategy.getBlockingQueue(maxQueueSize)
				: super.getBlockingQueue(maxQueueSize);
	}

	@Override
	public <T> HystrixRequestVariable<T> getRequestVariable(HystrixRequestVariableLifecycle<T> rv) {
		return existingStrategy != null ? existingStrategy.getRequestVariable(rv) : super.getRequestVariable(rv);
	}

	@Override
	public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey, HystrixProperty<Integer> corePoolSize,
			HystrixProperty<Integer> maximumPoolSize, HystrixProperty<Integer> keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		return existingStrategy != null
				? existingStrategy.getThreadPool(threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit,
						workQueue)
				: super.getThreadPool(threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	public <T> Callable<T> wrapCallable(Callable<T> callable) {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

		return existingStrategy != null
				? existingStrategy.wrapCallable(new WrapperCallable<T>(requestAttributes, callable))
				: super.wrapCallable(new WrapperCallable<T>(requestAttributes, callable));
	}

	private static class WrapperCallable<T> implements Callable<T> {

		private RequestAttributes requestAttributes;
		private Callable<T> target;

		WrapperCallable(RequestAttributes requestAttributes, Callable<T> target) {
			this.requestAttributes = requestAttributes;
			this.target = target;
		}

		@Override
		public T call() throws Exception {
			RequestContextHolder.setRequestAttributes(this.requestAttributes);

			try {
				return target.call();
			} finally {
				RequestContextHolder.resetRequestAttributes();
			}
		}
	}
}
