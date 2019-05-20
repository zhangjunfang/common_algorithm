public class RedPacket {

    /**
     * ���ɺ����Сֵ 1��
     */
    private static final int MIN_MONEY = 1;

    /**
     * ���ɺ�����ֵ 200�����
     */
    private static final int MAX_MONEY = 200 * 100;

    /**
     * С����Сֵ
     */
    private static final int LESS = -1;
    /**
     * �������ֵ
     */
    private static final int MORE = -2;

    /**
     * ����ֵ
     */
    private static final int OK = 1;

    /**
     * ���ĺ����ƽ��ֵ�� TIMES ������ֹĳһ�η������ϴ�
     */
    private static final double TIMES = 2.1F;

    private int recursiveCount = 0;

    public List<Integer> splitRedPacket(int money, int count) {
        List<Integer> moneys = new LinkedList<>();

        //����飬�������� * ���� < �ܽ�����Ҫ������С��� MAX_MONEY
        if (MAX_MONEY * count <= money) {
            System.err.println("�������С������ MAX_MONEY=[" + MAX_MONEY + "]");
            return moneys ;
        }


        //����������
        int max = (int) ((money / count) * TIMES);
        max = max > MAX_MONEY ? MAX_MONEY : max;

        for (int i = 0; i < count; i++) {
            //�����ȡ���
            int redPacket = randomRedPacket(money, MIN_MONEY, max, count - i);
            moneys.add(redPacket);
            //�ܽ��ÿ�μ���
            money -= redPacket;
        }

        return moneys;
    }

    private int randomRedPacket(int totalMoney, int minMoney, int maxMoney, int count) {
        //ֻ��һ�����ֱ�ӷ���
        if (count == 1) {
            return totalMoney;
        }

        if (minMoney == maxMoney) {
            return minMoney;
        }

        //�������������ʣ���� ����ʣ���� ��Ϊ��� money ÿ����һ�ζ����С
        maxMoney = maxMoney > totalMoney ? totalMoney : maxMoney;

        //�� minMoney��maxMoney ����һ��������
        int redPacket = (int) (Math.random() * (maxMoney - minMoney) + minMoney);

        int lastMoney = totalMoney - redPacket;

        int status = checkMoney(lastMoney, count - 1);

        //�������
        if (OK == status) {
            return redPacket;
        }

        //������ɵĽ��Ϸ� ��ݹ���������
        if (LESS == status) {
            recursiveCount++;
            System.out.println("recursiveCount==" + recursiveCount);
            return randomRedPacket(totalMoney, minMoney, redPacket, count);
        }

        if (MORE == status) {
            recursiveCount++;
            System.out.println("recursiveCount===" + recursiveCount);
            return randomRedPacket(totalMoney, redPacket, maxMoney, count);
        }

        return redPacket;
    }

    /**
     * У��ʣ��Ľ���ƽ��ֵ�Ƿ��� ��Сֵ�����ֵ�����Χ��
     *
     * @param lastMoney
     * @param count
     * @return
     */
    private int checkMoney(int lastMoney, int count) {
        double avg = lastMoney / count;
        if (avg < MIN_MONEY) {
            return LESS;
        }

        if (avg > MAX_MONEY) {
            return MORE;
        }

        return OK;
    }


    public static void main(String[] args) {
        RedPacket redPacket = new RedPacket();
        List<Integer> redPackets = redPacket.splitRedPacket(20000, 100);
        System.out.println(redPackets);

        int sum = 0;
        for (Integer red : redPackets) {
            sum += red;
        }
        System.out.println(sum);
    }

}