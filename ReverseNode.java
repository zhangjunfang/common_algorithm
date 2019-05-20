/**
 * Function: 三种方式反向打印单向链表
 *
 * @author zhangboyu
 * @since JDK 1.8
 */
public class ReverseNode {


    /**
     * 利用栈的先进后出特性
     * @param node
     */
    public void reverseNode1(Node node){

        System.out.println("====翻转之前====");

        Stack<Node> stack = new Stack<>() ;
        while (node != null){

            System.out.print(node.value + "===>");

            stack.push(node) ;
            node = node.next ;
        }

        System.out.println("");

        System.out.println("====翻转之后====");
        while (!stack.isEmpty()){
            System.out.print(stack.pop().value + "===>");
        }

    }


    /**
     * 利用头插法插入链表
     * @param head
     */
    public  void reverseNode(Node head) {
        if (head == null) {
            return ;
        }

        //最终翻转之后的 Node
        Node node ;

        Node pre = head;
        Node cur = head.next;
        Node next ;
        while(cur != null){
            next = cur.next;

            //链表的头插法
            cur.next = pre;
            pre = cur;

            cur = next;
        }
        head.next = null;
        node = pre;


        //遍历新链表
        while (node != null){
            System.out.println(node.value);
            node = node.next ;
        }

    }


    /**
     * 递归
     * @param node
     */
    public void recNode(Node node){

        if (node == null){
            return ;
        }

        if (node.next != null){
            recNode(node.next) ;
        }
        System.out.print(node.value+"===>");
    }


    public static class Node<T>{
        public T value;
        public Node<T> next ;


        public Node(T value, Node<T> next ) {
            this.next = next;
            this.value = value;
        }
    }
}
//单测
public class ReverseNodeTest {

    @Test
    public void reverseNode1() throws Exception {
        ReverseNode.Node<String> node4 = new Node<>("4",null) ;
        Node<String> node3 = new Node<>("3",node4);
        Node<String> node2 = new Node<>("2",node3);
        Node<String> node1 = new Node("1",node2) ;

        ReverseNode reverseNode = new ReverseNode() ;
        reverseNode.reverseNode1(node1);
    }

    @Test
    public void reverseNode12() throws Exception {

        Node<String> node1 = new Node("1",null) ;

        ReverseNode reverseNode = new ReverseNode() ;
        reverseNode.reverseNode1(node1);
    }

    @Test
    public void reverseNode13() throws Exception {

        Node<String> node1 = null ;

        ReverseNode reverseNode = new ReverseNode() ;
        reverseNode.reverseNode1(node1);
    }


    /**
     * 头插法
     * @throws Exception
     */
    @Test
    public void reverseHead21() throws Exception {
        Node<String> node4 = new Node<>("4",null) ;
        Node<String> node3 = new Node<>("3",node4);
        Node<String> node2 = new Node<>("2",node3);
        Node<String> node1 = new Node("1",node2) ;

        ReverseNode reverseNode = new ReverseNode() ;
        reverseNode.reverseNode(node1);

    }


    @Test
    public void recNodeTest31(){
        Node<String> node4 = new Node<>("4",null) ;
        Node<String> node3 = new Node<>("3",node4);
        Node<String> node2 = new Node<>("2",node3);
        Node<String> node1 = new Node("1",node2) ;

        ReverseNode reverseNode = new ReverseNode() ;
        reverseNode.recNode(node1);
    }

}
