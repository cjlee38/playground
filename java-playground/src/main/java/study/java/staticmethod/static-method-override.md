
* static method를 override(=hiding) 했을 때 바이트코드에서 어떻게 변화하는지 살펴본다.
* command : `javac StaticMethodOverride.java && javap -c -verbose StaticMethodOverride.class`

static method를 호출하면, invokestatic이 동작한다.
instant method를 호출하면, invokevirtual이 동작한다.
