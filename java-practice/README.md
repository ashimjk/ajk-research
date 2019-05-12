# Java Practice

## BigDecimal

Although, Float and Double can be used for decimal value. But it doesn't
provide the precision when doing calculation.
For Example :
```java
float x = 3.26f;
float y = 3.16f;
System.out.println(x-y);

// Output : 0.099999905

double x = 3.26f;
double b = 3.16f;
System.out.println(x-b);

// Output : 0.09999990463256836
```

Sometimes, it might be ok for not having precision. But there are times, when it really matters a lot. Like when it comes to monetary value. In financial transaction, all we need is precision.

This is the exact place for BigDecimal.

> Be Aware : Using float and double as a parameter for BigDecimal constructor repeats the same precision problem. Recommended approach is to use String.
```java
// Precision Problem
new BigDecimal(123.23);
new BigDecimal(123.23f);

// Recommended Approach
new BigDecimal("123.23");
```

### Features
    - Scaling and Rounding modes
    - No Overloaded operators
    - Use compareTo() to compare BigDecimals not equals()
    - BigDecimals are immutable
