package study.java.objectsize;

import java.lang.instrument.Instrumentation;
import java.time.LocalDateTime;

/*
제대로 동작하지 않는다. ClassNotFoundException 발생
javac InstrumentationAgent.java && jar cmf MANIFEST.MF InstrumentationAgent.jar InstrumentationAgent.class && java -javaagent:InstrumentationAgent.jar Test.java
 */
public class Test {

    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        instrumentation = inst;
    }

    public static long getObjectSize(Object o) {
        return instrumentation.getObjectSize(o);
    }

    static class AppointmentTime {

        private LocalDateTime startDateTime;
        private int durationMinutes;

        public AppointmentTime(LocalDateTime startDateTime, int durationMinutes) {
            this.startDateTime = startDateTime;
            this.durationMinutes = durationMinutes;
        }

    }

    public static void main(String[] args) {
        AppointmentTime appointmentTime = new AppointmentTime(LocalDateTime.now(), 1);
        long objectSize = getObjectSize(appointmentTime);
        System.out.println("objectSize = " + objectSize);
    }

}
