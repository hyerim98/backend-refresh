package nested.nested;

public class Network {
    private static int outClassVal = 3;
    private int outInstanceVal = 2;

    public void sendMsg(String text) {
        NetworkMsg msg = new NetworkMsg(text);
        msg.print();
    }

    private static class NetworkMsg { // 외부에서 사용하지 못하게 private로 막기
        private String msg;

        public NetworkMsg(String msg) {
            this.msg = msg;
        }

        public void print() {
            System.out.println("msg=" + msg);

            System.out.println(outClassVal); // 바깥 클래스의 클래스 멤버에는 접근 할 수 있다
            //System.out.println(outInstanceVal); 바깥 클래스의 인스턴스 멤버에는 접근할 수 없다
        }
    }
}
