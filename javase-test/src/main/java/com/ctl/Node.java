package com.ctl;



import org.apache.commons.beanutils.BeanUtils;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * <p>Title: T</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *  note  集合转数
 * @author guolin
 * @version 1.0
 * @date 2019-05-31 15:22
 */

public class Node {
    private Integer id;
    private Integer pid;
    private Integer seq;
    private List<Node> children;

    public Node() {
    }

    public Node(Integer id, Integer pid, Integer seq) {
        this.id = id;
        this.pid = pid;
        this.seq = seq;
    }

    public Node(Integer id, Integer pid, Integer seq, List<Node> children) {
        this.id = id;
        this.pid = pid;
        this.seq = seq;
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }


    public static void main(String[] args) {
        //保证allNode时有序的 根据pid和seq排序后的
        List<Node> allNode = new ArrayList<>();
        Node node1 = new Node(1001, null, 1);
        Node node2 = new Node(1002, null, 2);
        Node node3 = new Node(1003, null, 3);

        Node node1_001 = new Node(1001001, 1001, 1);
        Node node1_002 = new Node(1001002, 1001, 2);
        Node node1_003 = new Node(1001003, 1001, 3);

        Node node2_001 = new Node(1002001, 1002, 1);
        Node node2_002 = new Node(1002002, 1002, 2);

        Node node3_001 = new Node(1003001, 1003, 1);

        allNode.add(node1);
        allNode.add(node2);
        allNode.add(node3);

        allNode.add(node1_001);
        allNode.add(node1_002);
        allNode.add(node1_003);

        allNode.add(node2_001);
        allNode.add(node2_002);

        allNode.add(node3_001);


        List<Node> rootResourceList = new ArrayList<>();
        Deque<Node> deque = new ConcurrentLinkedDeque<>();

        if (allNode != null && allNode.size() > 0) {
            allNode.stream().forEach(resource -> {
                deque.add(resource);
            });
        }

        int size = allNode.size();
        for (int i = 0; i < size; i++) {
            Node node = deque.peek();
            if (node.getPid() == null || 0 == node.getPid()) {
                rootResourceList.add(node);
                deque.poll();
            } else {
                break;
            }

        }
        addResourceChild(rootResourceList, deque);
    }

    /**
     * list 转 tree
     *
     * @param resourceList
     * @param deque
     */
    private static synchronized void addResourceChild(List<Node> resourceList, Deque<Node> deque) {
        if (deque == null || resourceList == null || resourceList.size() == 0) {
            return;
        }
        try {
            int size = resourceList.size();
            for (int i = 0; i < size; i++) {
                Node node = resourceList.get(i);
                List<Node> children = new ArrayList<>();
                node.setChildren(children);

                Node poll = deque.poll();
                if (poll != null) {
                    Node pollRoot = new Node();
                    BeanUtils.copyProperties(poll, pollRoot);
                    int times = 1;
                    while (poll != null) {
                        if (poll.getId().intValue() == pollRoot.getId().intValue() && times > 1) {
                            deque.addFirst(poll);
                            break;
                        }
                        if (poll.getPid() != null && poll.getPid().intValue() == node.getId().intValue()) {
                            children.add(poll);
                        }
                        Node pollT = new Node();
                        BeanUtils.copyProperties(poll, pollT);
                        deque.add(pollT);
                        poll = deque.poll();
                        times++;
                    }
                }
                addResourceChild(children, deque);
            }
        } catch (Exception e) {

        }
    }

}
