package com.mubu2.Tree;  
  
import java.util.List;  
import java.awt.*;
import javax.swing.JPanel;  
  
/** 
 * TODO ͬһ���������BUG��Ӧ�ö�ÿһ������н�㶼���и���ͳ�ƣ�֮��Ż��ơ� 
 * @author John 
 * 
 */  
public class TreePanel extends JPanel {  
  
    private Node tree;              //����������  
    //private int gridWidth_0 = 200;
    //private int gridWidth_1 = 120;
    private int gridWidth = 80;     //ÿ�����Ŀ��  
    private int gridWidth_son;
    private int gridHeight_0=50;
    private int gridHeight_1=45;
    private int gridHeight = 38;//ÿ�����ĸ߶�
    private int gridHeightm[]= {50,30,20};
    private int vGap[] = {100,50,30,10,10,10};          //ÿ2�����Ĵ�ֱ����  
    private int hGap = 0;          //ÿ2������ˮƽ����  
      
    private int startY = 150;        //������Y��Ĭ�Ͼ��붥��10����  --------y�ľ��ж����Ƕ��٣�
    private int startX = 10;         //������X��Ĭ��ˮƽ���ж���  
      
    private int childAlign;                     //���Ӷ��뷽ʽ  
    public static int CHILD_ALIGN_ABSOLUTE = 0; //���Panel����  
    public static int CHILD_ALIGN_RELATIVE = 1; //��Ը�������  
      
    private Font font = new Font("΢���ź�",Font.BOLD,14);  //������������  
      
    private Color gridColor = Color.BLACK;      //��㱳����ɫ  
    private Color linkLineColor = Color.BLACK;  //���������ɫ  
    private Color stringColor = Color.WHITE;    //����������ֵ���ɫ  
      
    /** 
     * Ĭ�Ϲ��� 
     */  
    public TreePanel(){  
        this(null,CHILD_ALIGN_ABSOLUTE);  
    }  
      
    /** 
     * ���ݴ����Node���������Ծ��Ծ��еķ�ʽ���� 
     * @param n Ҫ���Ƶ��� 
     */  
    public TreePanel(Node n){  
        this(n,CHILD_ALIGN_ABSOLUTE);  
    }  
      
    /** 
     * ����Ҫ����ʱ��Ķ������ 
     * @param childAlign ������� 
     * @see tree.TreePanel#CHILD_ALIGN_RELATIVE 
     * @see tree.TreePanel#CHILD_ALIGN_ABSOLUTE 
     */  
    public TreePanel(int childAlign){  
        this(null,childAlign);  
    }  
      
    /** 
     * ���ݺ��Ӷ������childAlign���Ƶ����ĸ����n 
     * @param n Ҫ���Ƶ����ĸ���� 
     * @param childAlign ������� 
     */  
    public TreePanel(Node n, int childAlign){  
        super();  
        setTree(n);  
        this.childAlign = childAlign;  
        
    }  
      
    /** 
     * �������ڻ��Ƶ��� 
     * @param n ���ڻ��Ƶ����� 
     */  
    public void setTree(Node n) {  
        tree = n;  
    }  
      
    //��д���ѣ������Լ��Ļ��Ʒ���  
    public void paintComponent(Graphics g){  
        startX = (getWidth()-gridWidth)/2;  
        super.paintComponent(g);  
        g.setFont(font);  
        drawAllNode(tree, startX=50, startY=250,g);  
    }  
      
    /** 
     * �ݹ���������� 
     * @param n �����Ƶ�Node 
     * @param xPos ���ڵ�Ļ���Xλ�� 
     * @param g ��ͼ�����Ļ��� 
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
        	y= (n_layer-2)*(vGap+gridHeight)+2*vGap+gridHeight_0+gridHeight_1+startY;  //y = �㼶*(��ֱ���+���)+���ڵ����ʼy
            fontY = y + gridHeight - 5;     //5Ϊ���Եó���ֵ�������ͨ��FM�������ȷ�ģ�����Ӱ���ٶ�
        }
        //y + ÿ���ڵ�ĸ߶� -5
        
        */
        
        g.setColor(gridColor);  
        
        
        
        if( n_layer==0){
        	gridWidth=n.toString().length()*15;
        	g.fillRect(x, y, gridWidth, gridHeight_0);
        	g.setColor(stringColor);  
        	g.setFont(new Font("����", Font.BOLD,20));
        	g.drawString(n.toString(),x,y+gridHeight);       //���������� 
        	
        }
        else if (n_layer==1){
        	gridWidth=n.toString().length()*14;
        	g.fillRect(x, y, gridWidth, gridHeight_1);
        	g.setColor(stringColor);  
        	g.setFont(new Font("����", Font.BOLD, 18));
        	g.drawString(n.toString(),x+3, y+gridHeight_1-10);       //���������� 
        	

        }
        else {
        	gridWidth=n.toString().length()*14;
        	g.fillRect(x, y, gridWidth, gridHeight);
        	g.setColor(stringColor);  
        	g.setFont(new Font("����",Font.ROMAN_BASELINE, 15));
        	g.drawString(n.toString(), x+2, y+25);//gridHeight_0);       //���������� 
        	
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
                int newX = tempPosx+50;//(gridWidth_son+hGap)*i; //���ӽ����ʼX  
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
     * ���ý�㱳����ɫ 
     * @param gridColor ��㱳����ɫ 
     */  
    public void setGridColor(Color gridColor) {  
        this.gridColor = gridColor;  
    }  
  
    public Color getLinkLineColor() {  
        return linkLineColor;  
    }  
  
    /** 
     * ���ý�������ߵ���ɫ 
     * @param gridLinkLine ��������ߵ���ɫ 
     */  
    public void setLinkLineColor(Color gridLinkLine) {  
        this.linkLineColor = gridLinkLine;  
    }  
  
    public Color getStringColor() {  
        return stringColor;  
    }  
  
    /** 
     * ���ý����������ɫ 
     * @param stringColor �����������ɫ 
     */  
    public void setStringColor(Color stringColor) {  
        this.stringColor = stringColor;  
    }  
      
    public int getStartY() {  
        return startY;  
    }  
  
    /** 
     * ���ø�����Yλ�� 
     * @param startY ������Yλ�� 
     */  
    public void setStartY(int startY) {  
        this.startY = startY;  
    }  
  
    public int getStartX() {  
        return startX;  
    }  
  
    /** 
     * ���ø�����Xλ�� 
     * @param startX ������Xλ�� 
     */  
    public void setStartX(int startX) {  
        this.startX = startX;  
    }  
  
      
}  