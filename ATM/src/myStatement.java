import java.io.*;

public class myStatement  extends statementFactory {

        private static myStatement uniqueInstance;
        private myStatement() {}
        private static Object sync;

        public static myStatement getUniqueInstance() {
            synchronized(sync) {
                if (uniqueInstance == null) {
                    uniqueInstance = new myStatement();
                }
            }
            return uniqueInstance;
        }

        public statementType createStatements(String selection) {
            if (selection.equalsIgnoreCase("detailedStmt")) {
                return new DetailedStatement();
            } else if (selection.equalsIgnoreCase("miniStmt")) {
                return new MiniStatement();
            }
            throw new IllegalArgumentException("Selection doesnot exist");

        }

        static class DetailedStatement implements statementType {
            @Override
            public String print() {
                return "detailedStmt";
            }
        }

        static class MiniStatement implements statementType {
            @Override
            public String print() {
                return "miniStmt";
            }

        }

    }
