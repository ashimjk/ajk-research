# Spring Transaction

## Summary
- Local Test : to check service level transactional
- Propagation Test : check Propagation.REQUIRED and Propagation.REQUIRED_NEW

## Note
- TransactionInterceptor intercept insert call and delegate request to TransactionAspectSupport.invokeWithinTransaction
- TransactionAspectSupport.invokeWithinTransaction
	- get PlatformTransactionManager and joinpointIdentification (UserDao.insert)
	- get TransactionAttribute (PROPAGATION_REQUIRED,ISOLATION_DEFAULT)
	- get TransactionInfo (PROPAGATION_REQUIRED,ISOLATION_DEFAULT)

	- createTransactionIfNecessary
		// use JpaTransactionManager to get transaction
		- AbstractPlatformTransactionManager.getTransaction
			- JpaTransactionManager.doGetTransaction
				- set EntityManagerHolder
				- set savePointAllowed
				- set ConnectionHolder
				- return JpaTransactionObject
			- check TransactionAttribute for propagation level or isolation level and return TransactionStatus
			- begin transaction
			- prepare synchronization
		- prepareTransactionInfo
			- create TransactionInfo
			- bind TransactionStatus to TransactionInfo
			- bind TransactionInfo into current thread transactionInfoHolder (ThreadLocal<TransactionAspectSupport.TransactionInfo>)
	// now transaction is in new state
	// now invoke dao method (UserDao.insert() method)
	-  delegate request to TransactionInterceptor

	// if transaction failed
	- JpaTransactionManager.rollback
	- restore previous TransactionInfo into current thread transactionInfoHolder (ThreadLocal<TransactionAspectSupport.TransactionInfo>)

	// if transaction success
	- restore previous TransactionInfo into current thread transactionInfoHolder (ThreadLocal<TransactionAspectSupport.TransactionInfo>)
	- JpaTransactionManager.commit

```log
-- stacktrace
insert(List):27, UserDao {io.ashimjk.transaction.sample.dao}, UserDao.java
invoke(int, Object, Object[]):-1, UserDao$$FastClassBySpringCGLIB$$45f0b8c1 {io.ashimjk.transaction.sample.dao}, <generated>
invoke(Object, Object[]):218, MethodProxy {org.springframework.cglib.proxy}, MethodProxy.java
invokeJoinpoint():749, CglibAopProxy$CglibMethodInvocation {org.springframework.aop.framework}, CglibAopProxy.java
proceed():163, ReflectiveMethodInvocation {org.springframework.aop.framework}, ReflectiveMethodInvocation.java
proceedWithInvocation():-1, 318787032 {org.springframework.transaction.interceptor.TransactionInterceptor$$Lambda$468}, Unknown Source

invokeWithinTransaction(Method, Class, TransactionAspectSupport$InvocationCallback):294, TransactionAspectSupport {org.springframework.transaction.interceptor}, TransactionAspectSupport.java

invoke(MethodInvocation):98, TransactionInterceptor {org.springframework.transaction.interceptor}, TransactionInterceptor.java
proceed():186, ReflectiveMethodInvocation {org.springframework.aop.framework}, ReflectiveMethodInvocation.java
intercept(Object, Method, Object[], MethodProxy):688, CglibAopProxy$DynamicAdvisedInterceptor {org.springframework.aop.framework}, CglibAopProxy.java
insert(List):-1, UserDao$$EnhancerBySpringCGLIB$$6dcf2939 {io.ashimjk.transaction.sample.dao}, <generated>
insert():26, UserService {io.ashimjk.transaction.sample.service}, UserService.java
```
