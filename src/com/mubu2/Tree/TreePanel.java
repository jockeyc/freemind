package com.mubu2.Tree;  
  
import java.util.List;  
import java.awt.*;
import javax.swing.JPanel;  
  
/** 
 * TODO 同一层结点过多有BUG，应该对每一层的所有结点都进行个数统计，之后才绘制。 
 * @author John 
 * 
 */  
public class TreePanel extends JPanel {  
  
    private Node tree;              //保存整棵树  
    //private int gridWidth_0 = 200;
    //private int gridWidth_1 = 120;
    private int gridWidth = 80;     //每个结点的宽度  
    private int gridWidth_son;
    private int gridHeight_0=50;
    private int gridHeight_1=45;
    private int gridHeight = 38;//每个结点的高度
    private int gridHeightm[]= {50,30,20};
    private int vGap[] = {100,50,30,10,10,10};          //每2个结点的垂直距离  
    private int hGap = 0;          //每2个结点的水平距离  
      
    private int startY = 150;        //根结点的Y，默认距离顶部10像素  --------y的居中对齐是多少？
    private int startX = 10;         //根结点的X，默认水平居中对齐  
      
    private int childAlign;                     //孩子对齐方式  
    public static int CHILD_ALIGN_ABSOLUTE = 0; //相对Panel居中  
    public static int CHILD_ALIGN_RELATIVE = 1; //相对父结点居中  
      
    private Font font = new Font("微软雅黑",Font.BOLD,14);  //描述结点的字体  
      
    private Color gridColor = Color.BLACK;      //结点背景颜色  
    private Color linkLineColor = Color.BLACK;  //结点连线颜色  
    private Color stringColor = Color.WHITE;    //结点描述文字的颜色  
      
    /** 
     * 默认构造 
     */  
    public TreePanel(){  
        this(null,CHILD_ALIGN_ABSOLUTE);  
    }  
      
    /** 
     * 根据传入的Node绘制树，以绝对居中的方式绘制 
     * @param n 要绘制的树 
     */  
    public TreePanel(Node n){  
        this(n,CHILD_ALIGN_ABSOLUTE);  
    }  
      
    /** 
     * 设置要绘制时候的对齐策略 
     * @param childAlign 对齐策略 
     * @see tree.TreePanel#CHILD_ALIGN_RELATIVE 
     * @see tree.TreePanel#CHILD_ALIGN_ABSOLUTE 
     */  
    public TreePanel(int childAlign){  
        this(null,childAlign);  
    }  
      
    /** 
     * 根据孩子对齐策略childAlign绘制的树的根结点n 
     * @param n 要绘制的树的根结点 
     * @param childAlign 对齐策略 
     */  
    public TreePanel(Node n, int childAlign){  
        super();  
        setTree(n);  
        this.childAlign = childAlign;  
        
    }  
      
    /** 
     * 设置用于绘制的树 
     * @param n 用于绘制的树的 
     */  
    public void setTree(Node n) {  
        tree = n;  
    }  
      
    //重写而已，调用自己的绘制方法  
    public void paintComponent(Graphics g){  
        startX = (getWidth()-gridWidth)/2;  
        super.paintComponent(g);  
        g.setFont(font);  
        drawAllNode(tree, startX=50, startY=250,g);  
    }  
      
    /** 
     * 递归绘制整棵树 
     * @param n 被绘制的Node 
     * @param xPos 根节点的绘制X位置 
     * @param g 绘图上下文环境 
     */  
    public int getWidth_fs(Node n)
    {	int Wid=0;
    	if(n.getLayer()==1)
    		 Wid = n.toString().length()*18;
    	else {
			Wid= n.toString().length()*12;
		}
    	return Wid;
    	
    }
    public void drawAllNode(Node n, int x,int y, Graphics g){  
       /*
        *        int fontX;   
        */
        int n_layer=n.getLayer();
       /* if( n_layer==0){
        		x =startX;
        		fontX = x+gridHeight_0-5;
        }
        else if (n_layer==1){
    		x =startX + vGap+gridHeight_0;
    		fontY = y+gridHeight_1-5;
        }
        else {
        	y= (n_layer-2)*(vGap+gridHeight)+2*vGap+gridHeight_0+gridHeight_1+startY;  //y = 层级*(垂直间隔+宽度)+根节点的起始y
            fontY = y + gridHeight - 5;     //5为测试得出的值，你可以通过FM计算更精确的，但会影响速度
        }
        //y + 每个节点的高度 -5
        
        */
        
        g.setColor(gridColor);  
        
        
        
        if( n_layer==0){
        	gridWidth=n.toString().length()*15;
        	g.fillRect(x, y, gridWidth, gridHeight_0);
        	g.setColor(stringColor);  
        	g.setFont(new Font("宋体", Font.BOLD,20));
        	g.drawString(n.toString(),x,y+gridHeight);       //画结点的名字 
        	
        }
        else if (n_layer==1){
        	gridWidth=n.toString().length()*14;
        	g.fillRect(x, y, gridWidth, gridHeight_1);
        	g.setColor(stringColor);  
        	g.setFont(new Font("宋体", Font.BOLD, 18));
        	g.drawString(n.toString(),x+3, y+gridHeight_1-10);       //画结点的名字 
        	

        }
        else {
        	gridWidth=n.toString().length()*14;
        	g.fillRect(x, y, gridWidth, gridHeight);
        	g.setColor(stringColor);  
        	g.setFont(new Font("宋体",Font.ROMAN_BASELINE, 15));
        	g.drawString(n.toString(), x+2, y+25);//gridHeight_0);       //画结点的名字 
        	
        }
          
        
        int gridWidth_p=gridWidth;
        

		if(n.hasChild()){  
        	g.setColor(Color.red);
        	if(n_layer==0)
        	  g.drawLine(x+gridWidth, y+gridHeight_0/2, x+gridWidth+40, y+gridHeight_0/2);
        	else if(n_layer==1)
          	  g.drawLine(x+gridWidth, y+gridHeight_1/2, x+gridWidth+40, y+gridHeight_1/2);
        	else
          	  g.drawLine(x+gridWidth, y+gridHeight/2, x+gridWidth+40, y+gridHeight/2);
            List<Node> c = n.getChilds();  
            int size = n.getChilds().size();  
            gridWidth_son=0;
            System.out.print(size);
            int tempPosy = 0;   
            int tempPosx = x+ gridWidth;
            g.setColor(Color.yellow);
            if(n_layer==0)
            {tempPosy=y+gridHeight_0/2-(size*(gridHeight_1+vGap[n.getLayer()])-vGap[n.getLayer()])/2;
             g.drawLine(x+gridWidth+40, tempPosy+gridHeight_1/2, x+gridWidth+40, tempPosy+gridHeight_1/2+(size-1)*(gridHeight_1+vGap[n.getLayer()]));
            }
            else if(n_layer==1)
            {	tempPosy=y+ gridHeight_1/2-(size*(gridHeight+vGap[n.getLayer()])-vGap[n.getLayer()])/2; 
          		g.drawLine(x+gridWidth+40, tempPosy+gridHeight/2, x+gridWidth+40, tempPosy+gridHeight/2+(size-1)*(gridHeight+vGap[n.getLayer()]) );
            }else
            {	tempPosy=y+ gridHeight/2-(size*(gridHeight+vGap[n.getLayer()])-vGap[n.getLayer()])/2;
          		g.drawLine(x+gridWidth+40, tempPosy+gridHeight/2, x+gridWidth+40, tempPosy+gridHeight/2+(size-1)*(gridHeight+vGap[n.getLayer()]));
            }
            /*childAlign == CHILD_ALIGN_RELATIVE   ? x+gridWidth/2 - (size*(gridWidth_son+hGap)-hGap)/2  
                         
                         : (getWidth() - size*(gridWidth_son+hGap)+hGap)/2;   */
            int interpointx= x+gridWidth+40; 
            int i = 0;  
                   
            for(Node node : c){  
            	 g.setColor(Color.blue);
                int newX = tempPosx+50;//(gridWidth_son+hGap)*i; //孩子结点起始X  
                int newY = 0;
                if (node.getLayer()==1&&i!=0)
                	newY = tempPosy+(gridHeight_1+vGap[n.getLayer()])*i;
                else 
                	newY= tempPosy+(gridHeight+vGap[n.getLayer()])*i;;
                if(node.getLayer()==1)
                	g.drawLine(interpointx, newY+gridHeight_1/2, interpointx+40, newY+gridHeight_1/2);
                else
                g.drawLine(interpointx, newY+gridHeight/2, interpointx+40, newY+gridHeight/2);
                g.setColor(linkLineColor);  
                drawAllNode(node, newX,newY, g);  
                i++;  
            }  
        }  
    }  
    public Color getGridColor() {  
        return gridColor;  
    }  
  
    /** 
     * 设置结点背景颜色 
     * @param gridColor 结点背景颜色 
     */  
    public void setGridColor(Color gridColor) {  
        this.gridColor = gridColor;  
    }  
  
    public Color getLinkLineColor() {  
        return linkLineColor;  
    }  
  
    /** 
     * 设置结点连接线的颜色 
     * @param gridLinkLine 结点连接线的颜色 
     */  
    public void setLinkLineColor(Color gridLinkLine) {  
        this.linkLineColor = gridLinkLine;  
    }  
  
    public Color getStringColor() {  
        return stringColor;  
    }  
  
    /** 
     * 设置结点描述的颜色 
     * @param stringColor 结点描述的颜色 
     */  
    public void setStringColor(Color stringColor) {  
        this.stringColor = stringColor;  
    }  
      
    public int getStartY() {  
        return startY;  
    }  
  
    /** 
     * 设置根结点的Y位置 
     * @param startY 根结点的Y位置 
     */  
    public void setStartY(int startY) {  
        this.startY = startY;  
    }  
  
    public int getStartX() {  
        return startX;  
    }  
  
    /** 
     * 设置根结点的X位置 
     * @param startX 根结点的X位置 
     */  
    public void setStartX(int startX) {  
        this.startX = startX;  
    }  
  
      
}  