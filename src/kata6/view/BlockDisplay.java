package kata6.view;

public interface BlockDisplay {
    void displayBlock(int x, int y);
    void on(Moved moved);
    
    interface Moved {
        void to (int x, int y);

        public static class Null implements Moved {
            @Override
            public void to(int x, int y) {
            }
        }
    }
}