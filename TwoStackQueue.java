/**
 * Function: 两个栈实现队列
 *
 * 利用两个栈来实现，第一个栈存放写队列的数据。
 * 第二个栈存放移除队列的数据，移除之前先判断第二个栈里是否有数据。
 * 如果没有就要将第一个栈里的数据依次弹出压入第二个栈，这样写入之后的顺序再弹出其实就是一个先进先出的结构了。
 *
 * 这样出队列只需要移除第二个栈的头元素即可。
 *
 * @author zhangboyu
 * @since JDK 1.8
 */
public class TwoStackQueue<T> {

    /**
     * 写入的栈
     */
    private Stack<T> input = new Stack() ;

    /**
     * 移除队列所出的栈
     */
    private Stack<T> out = new Stack() ;


    /**
     * 写入队列
     * @param t
     */
    public void appendTail(T t){
        input.push(t) ;
    }

    /**
     * 删除队列头结点 并返回删除数据
     * @return
     */
    public T deleteHead(){

        //是空的 需要将 input 出栈写入 out
        if (out.isEmpty()){
            while (!input.isEmpty()){
                out.push(input.pop()) ;
            }
        }

        //不为空时直接移除出栈就表示移除了头结点
        return out.pop() ;
    }


    public int getSize(){
        return input.size() + out.size() ;
    }

}
//单测
public class TwoStackQueueTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(TwoStackQueueTest.class);
    @Test
    public void queue(){
        TwoStackQueue<String> twoStackQueue = new TwoStackQueue<String>() ;
        twoStackQueue.appendTail("1") ;
        twoStackQueue.appendTail("2") ;
        twoStackQueue.appendTail("3") ;
        twoStackQueue.appendTail("4") ;
        twoStackQueue.appendTail("5") ;


        int size = twoStackQueue.getSize();

        for (int i = 0; i< size ; i++){
            LOGGER.info(twoStackQueue.deleteHead());
        }

        LOGGER.info("========第二次添加=========");

        twoStackQueue.appendTail("6") ;

        size = twoStackQueue.getSize();

        for (int i = 0; i< size ; i++){
            LOGGER.info(twoStackQueue.deleteHead());
        }
    }

}
