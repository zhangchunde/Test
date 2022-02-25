package com.yimo.datastructure.tree;

public class AVLTree {

    private Node root;

    class Node{
        private Node left;
        private Node right;
        private Node parent;
        private Node pre;
        private Node next;

        private int hash;
        private Object key;
        private Object value;

        public Node(Node left, Node right, Node pre, Node next, int hash, Object key, Object value) {
            this.left = left;
            this.right = right;
            this.pre = pre;
            this.next = next;
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public Node find(Node node){

            if(node == null){
                return null;
            }

            return this.find(node.key);

        }

        public Node find(Object key){

            if(key == null){
                return null;
            }

            int k_hash = key.hashCode();
            int this_hash = this.hash;

            if(this_hash == k_hash){
                return this;
            }else if(k_hash < this_hash && this.left != null){

                return this.left.find(key);
            }else if(k_hash > this_hash && this.right != null){

                return this.right.find(key);
            }

            return null;
        }


    }

    public Object get(Object key){
        Node r;
        if((r = this.root.find(key)) != null){
            return r.value;
        }
        return null;
    }

    public Object putVar(Object key, Object value) throws Exception {

        if(key == null){
            throw new Exception("key不能为Null");
        }

        Node p = this.root;
        Node x = new Node(null,null,null,null,key.hashCode(),key,value);

        int k_hash = key.hashCode();
        int p_hash;

        if(p == null){
            this.root = x;
        }else{

            do{
                p_hash = p.hash;
                if(p_hash == k_hash){
                    Object v = p.value;
                    p.value = value;
                    return v;
                }else if(k_hash < p_hash){
                    if(p.left == null){
                        p.left = x;
                        x.parent = p;

                        Node p_n = p.next;
                        p.next = x;
                        x.next = p_n;
                        p_n.pre = x;
                        x.pre = p;
                        // 旋转树结构
                        balanceTree();
                        break;
                    }
                    p = p.left;
                }else{
                    if(p.right == null){
                        p.right = x;
                        x.parent = p;

                        Node p_n = p.next;
                        p.next = x;
                        x.next = p_n;
                        p_n.pre = x;
                        x.pre = p;
                        // 旋转树结构
                        balanceTree();
                        break;
                    }
                    p = p.right;
                }

            }while(true);

        }
        return null;
    }

    public void balanceTree(){

    }

}
