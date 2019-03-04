package com.mubu2.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MuxTree {
	private String data;
    public MuxTree parent;
    public List<MuxTree> children;
    public MuxTree(String data){
        this.data = data;
        this.parent = null;
        this.children = new LinkedList<>();
    }
    public MuxTree(String data, MuxTree parent){
        this.data = new String(data);
        this.parent = parent;
        this.children = new LinkedList<>();
    }
    public MuxTree insert(String data, MuxTree parent){
        MuxTree child = new MuxTree(data, parent);
        parent.children.add(child);
        return child;
    }
    public String getData()
    {
    	return data;
    }
    public MuxTree delete(MuxTree parent, int index){
        return parent.children.remove(index);
    }
    public boolean delete(MuxTree parent, MuxTree child){
        return parent.children.remove(child);
    }
    public MuxTree search(String myString){
        Queue<MuxTree> queue = new LinkedList<>();
        MuxTree p = this;
        String s = p.data.toString();
        while(p != null){
            if(s.equals(myString.toString())){
                return  p;
            }
            if(p.children != null){
                for(MuxTree temp : p.children){
                    ((LinkedList<MuxTree>) queue).add(temp);
                }
            }
            p = queue.poll();
        }
        return null;
    }
    public void preOrder(){
        this.preOrder(this);
        return ;
    }
    public void preOrder(MuxTree temp){
        if(temp == null)	
        	return;   
        MuxTree p = temp;
        int parentNum = 0;
        if(p.parent != null){
            while(p.parent != null){
                parentNum += p.data.toString().length();
                p = p.parent;
            }
            for(int i = 0; i < parentNum; i++){
                System.out.print(" ");
            }
            System.out.println(temp.data);
        }
        for(MuxTree index : temp.children){
            preOrder(index);
        }
        return ;
    }
    public Node convert_toNode(MuxTree temp) {
    	if(temp == null)
    		return null;
    	Node a=new Node(temp.data);
    	for(MuxTree index: temp.children)
    		a.add(convert_toNode(index));
    	return a;
    }
}
