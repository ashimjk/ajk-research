# Jackson Object Merge

## With @JsonMerge
```
input json: {"name":"Jake","salary":3000,"address":{"street":"101 Blue Dr","city":"White Smoke"}}
existing object : Employee{name='John', dept='Dev', salary=1000, phone='222-222-222', address=Address{street='101 Blue Dr', city='SunBridge', zipCode=23456}}
existing object hascode: 1956725890
existing object nested (Address) hascode: 356573597
updated object : Employee{name='Jake', dept='Dev', salary=3000, phone='222-222-222', address=Address{street='101 Blue Dr', city='White Smoke', zipCode=23456}}
updated object hascode: 1956725890
updated object nested (Address) hascode: 356573597
```

### Comparision
```
input json: {"name":"Jake","salary":3000,"address":{"street":"101 Blue Dr","city":"White Smoke"}}
existing object : Employee{name='John', dept='Dev', salary=1000, phone='222-222-222', address=Address{street='101 Blue Dr', city='SunBridge', zipCode=23456}}
updated object :  Employee{name='Jake', dept='Dev', salary=3000, phone='222-222-222', address=Address{street='101 Blue Dr', city='White Smoke', zipCode=23456}}
```

## Without @JsonMerge
```
input json: {"name":"Jake","salary":3000,"address":{"street":"101 Blue Dr","city":"White Smoke"}}
existing object : Employee{name='John', dept='Dev', salary=1000, phone='222-222-222', address=Address{street='101 Blue Dr', city='SunBridge', zipCode=23456}}
existing object hascode: 1956725890
existing object nested (Address) hascode: 356573597
updated object : Employee{name='Jake', dept='Dev', salary=3000, phone='222-222-222', address=Address{street='101 Blue Dr', city='White Smoke', zipCode=null}}
updated object hascode: 1956725890
updated object nested (Address) hascode: 519821334
```

### Comparision
```
input json: {"name":"Jake","salary":3000,"address":{"street":"101 Blue Dr","city":"White Smoke"}}
existing object : Employee{name='John', dept='Dev', salary=1000, phone='222-222-222', address=Address{street='101 Blue Dr', city='SunBridge', zipCode=23456}}
updated object :  Employee{name='Jake', dept='Dev', salary=3000, phone='222-222-222', address=Address{street='101 Blue Dr', city='White Smoke', zipCode=null}} 
```