// Current Time Programming Excercise / Jake Roman
class CurrentTime {
    static long timeZone = -4; // UTC-5 time code

    public static void main(String[] args) {
        // get current epoch time
        long epoch = System.currentTimeMillis();
        
        // process the time
        long sec = epoch/1000; // convert ms to s
        long dayLengthInSeconds = 60*60*24;
        long time = (sec % dayLengthInSeconds);

        long timeH = (time/3600)+timeZone;
        long timeM = (time%3600)/60;
        long timeS = (time%60);

        System.out.println(timeH + ":" + timeM + ":" + timeS);
    }
}
