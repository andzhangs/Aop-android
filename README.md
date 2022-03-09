# AOP的基本知识
###### 在使用AspectJ之前，还是需要先介绍下AOP的基本知识，熟悉的看官可以跳过这部分。

"
    切点表达式是什么？</br>
        execution(<修饰符模式>? <返回类型模式> <方法名模式>(<参数模式>) <异常模式>?)</br>
        除了返回类型模式、方法名模式和参数模式外，其它项都是可选的。</br>
        修饰符模式指的是public、private、protected，异常模式指的是NullPointException等。</br>
"

## 1、AOP术语
1. **通知、增强处理（Advice）**：就是你想要的功能，也就是上面说的日志、耗时计算等。
2. **连接点（JoinPoint）**：允许你通知（Advice）的地方，那可就真多了，基本每个方法的前、后（两者都有也行），或抛出异常是时都可以是连接点（spring只支持方法连接点）。AspectJ还可以让你在构造器或属性注入时都行，不过一般情况下不会这么做，只要记住，和方法有关的前前后后都是连接点。
3. **切入点（Pointcut）**：上面说的连接点的基础上，来定义切入点，你的一个类里，有15个方法，那就有十几个连接点了对吧，但是你并不想在所有方法附件都使用通知（使用叫织入，下面再说），你只是想让其中几个，在调用这几个方法之前、之后或者抛出异常时干点什么，那么就用切入点来定义这几个方法，让切点来筛选连接点，选中那几个你想要的方法。
4. **切面（Aspect）**：切面是通知和切入点的结合。现在发现了吧，没连接点什么事，连接点就是为了让你好理解切点搞出来的，明白这个概念就行了。通知说明了干什么和什么时候干（什么时候通过before，after，around等AOP注解就能知道），而切入点说明了在哪干（指定到底是哪个方法），这就是一个完整的切面定义。
5. **织入（weaving）**：把切面应用到目标对象来创建新的代理对象的过程。</br>
   上述术语的解释引用自《AOP中的概念通知、切点、切面》这篇文章，作者的描述非常直白，很容易理解，点个赞。

## 2、AOP注解与使用
+ @Aspect：声明切面，标记类
+ @Pointcut(切点表达式)：定义切点，标记方法
+ @Before(切点表达式)：前置通知，切点之前执行
+ @Around(切点表达式)：环绕通知，切点前后执行
+ @After(切点表达式)：后置通知，切点之后执行
+ @AfterReturning(切点表达式)：返回通知，切点方法返回结果之后执行
+ @AfterThrowing(切点表达式)：异常通知，切点抛出异常时执行

## 3、JoinPoint的作用
+ MethodSignature signature = (MethodSignature) joinPoint.getSignature();
+ String name = signature.getName(); // 方法名：test
+ Method method = signature.getMethod(); // 方法：public void com.lqr.androidaopdemo.MainActivity.test(android.view.View)
+ Class returnType = signature.getReturnType(); // 返回值类型：void
+ Class declaringType = signature.getDeclaringType(); // 方法所在类名：MainActivity
+ String[] parameterNames = signature.getParameterNames(); // 参数名：view
+ Class[] parameterTypes = signature.getParameterTypes(); // 参数类型：View