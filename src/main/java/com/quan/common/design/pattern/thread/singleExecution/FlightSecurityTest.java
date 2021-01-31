package com.quan.common.design.pattern.thread.singleExecution;

public class FlightSecurityTest {

    // 旅客线程
    static class Passengers extends Thread {
        // 机场案件类
        private final FlightSecurity flightSecurity;
        // 旅客身份证
        private final String idCard;
        // 旅客登机牌
        private final String boardingPass;

        public Passengers(FlightSecurity flightSecurity, String idCard, String boardingPass) {
            this.flightSecurity = flightSecurity;
            this.boardingPass = boardingPass;
            this.idCard = idCard;
        }

        @Override
        public void run() {
            while (true) {
                // 旅客不断地通过安检
                flightSecurity.pass(boardingPass, idCard);
            }
        }

        public static void main(String[] args) {
            final FlightSecurity flightSecurity = new FlightSecurity();
            new Passengers(flightSecurity, "A123456", "AF123456").start();
            new Passengers(flightSecurity, "B123456", "BF123456").start();
            new Passengers(flightSecurity, "C123456", "CF123456").start();
        }
    }
}
