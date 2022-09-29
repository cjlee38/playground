
# IO 작업이 없는 상황에서 멀티스레드가 빠른 동작을 보장할까 ?

## 1차 테스트

[테스트 코드](../spring-playground/src/main/java/study/java/mutlithread/WhichIsFasterWithoutIO.java)


- 싱글 스레드 : average time = 2778672562
- 멀티 스레드(4): average time = 1371448083

## 2차 테스트(우분투)
threadCount = 1, size = 10000000, iterateCount = 10, average time = 4231744360
threadCount = 4, size = 10000000, iterateCount = 10, average time = 1222231425

아마 멀티코어 환경이라 더 빠른 결과가 나온 것 같다.  
리눅스의 taskset 명령어를 활용해보자.(`taskset <COREMASK> <EXECUTABLE>`)

- 다음과 같이 사용한다.
- `taskset 0x1 java WhichIsFasterWithoutIO.java`
  threadCount = 1, size = 10000000, iterateCount = 10, average time = 4364312972
  threadCount = 4, size = 10000000, iterateCount = 10, average time = 4281376235

## 결론
단일 스레드 환경에서는 속도의 차이가 없다.
