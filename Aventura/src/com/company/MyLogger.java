package com.company;

import java.util.logging.*;

public class MyLogger{


    public static class MyHandler extends StreamHandler {

        @Override
        public void publish(LogRecord record) {
            //add own logic to publish
            super.publish(record);
        }

        @Override
        public void flush() {
            super.flush();
        }

        @Override
        public void close() throws SecurityException {
            super.close();
        }

    }

   public static class MyFormatter extends Formatter {

        @Override
        public String format(LogRecord record) {
            return record.getMessage()+"\n";
        }
    }

    public static class MyFilter implements Filter {

        @Override
        public boolean isLoggable(LogRecord log) {
            //don't log CONFIG logs in file
            return log.getLevel() != Level.CONFIG;
        }
    }
}
