
# IO 작업이 없는 상황에서 멀티스레드가 빠른 동작을 보장할까 ?

[테스트 코드](../java-playground/src/main/java/study/java/mutlithread/WhichIsFasterWithoutIO.java)

## 예상 
- 스레드는 IO 작업이 없다면 block 되지 않기 때문에 항상 running 상태일 것이다.(not ready or sleep)
- 따라서 멀티 스레드라고 하더라도 큰 차이는 존재하지 않을 것이다.

## 공통 실행 환경
- jdk 11

## 1차 테스트(M1 칩, 멀티코어)
- 싱글 스레드 : average time = 2778672562
- 멀티 스레드(4): average time = 1371448083

- 맥북에 내장된 CPU 가 멀티코어(8) 이기 때문에 속도의 차이가 있는 것으로 추측된다.  
- 우분투의 taskset 명령어를 활용해 싱글코어에서 실행해보자.
  - 맥에는 taskset 명령어가 존재하지 않는다..더라

## 2차 테스트(우분투 20.04, 멀티코어)
- 싱글 스레드 : average time = 4231744360
- 멀티 스레드 : average time = 1222231425

- 싱글 코어로 실행해보기 전에, 우분투(도커)에서 먼저 한번 실행해보았다.
- 역시 마찬가지로 멀티 스레드에서 더 빠른 결과가 나오는 것을 확인해볼 수 있다.

## 3차 테스트(우분투 20.04, 싱글코어)
리눅스의 taskset 명령어를 활용해보자.(`taskset <COREMASK> <EXECUTABLE>`)

- 다음과 같이 사용한다.
- `taskset 0x1 java WhichIsFasterWithoutIO.java`
- 결과는 다음과 같다.
- 싱글 스레드 : average time = 4364312972
- 멀티 스레드 : average time = 4281376235

## 결론
- 싱글 코어 기준으로 멀티 스레드가 더 빠르다는 가설을 기각할 수 있다.
