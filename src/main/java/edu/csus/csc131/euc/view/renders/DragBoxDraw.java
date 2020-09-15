package edu.csus.csc131.euc.view.renders;

/* Library Imports */
import javax.swing.*; 
import java.awt.*;

// Private Class to draw the DragBox using Graphic override
public class DragBoxDraw extends JPanel {

    // Seriable ID 
    private static final long serialVersionUID = 1L;

    /* Constants */ 
    private Dimension size;
    private Point rectorigin; 
    private Point filelabelorigin;

    // Initialize Panel to draw the drag box
    public DragBoxDraw(Dimension s){
        this.size = s; 

        // Set Background of panel
        this.setBackground(Color.WHITE);

        // Set panel to the preffered size 
        this.setPreferredSize(s);
        this.setSize(s);

        // Initialize Origins
        this.rectorigin = new Point(0, 0);
    
        // This sets the label orgin centered by inputted dimension for dragbox 
        this.filelabelorigin = new Point((int) s.getWidth()/2 - 120, (int) s.getHeight()/2 + 5); 
    }

    @Override
    protected void paintComponent(Graphics g) {

        // Setting Rectangle Parameters 
        Graphics2D g2 = (Graphics2D) g; 
        super.paintComponent(g2); 

        // Rectangle Attributes 
        int thickness = 2; 

        // Create Rectangle 
        Rectangle rect = new Rectangle(
            rectorigin.x, 
            rectorigin.y , 
            size.width-1, 
            size.height-1
        );

        // Create Border 
        float dash1[] = {10.0f};
        BasicStroke dashed = new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f); 

        // Update Parameters 
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(thickness));
        g2.setStroke(dashed);

        // Draw Rectangle 
        g2.draw(rect);

        // String Attributes 
        g2.setColor(Color.black);
        g2.setFont(new Font("Poppins", Font.PLAIN, 20));

        // Draw Rectangle String 
        g2.drawString("Browse or Drag Files Here", filelabelorigin.x, filelabelorigin.y);

    }

    // Getters
    public Dimension getSize(){ return this.size; }
    public Point getRectOrigin(){ return this.rectorigin; }

    // Setters
    public void setSize(Dimension s){ this.size = s; }
    public void setRectOrigin(Point p){ this.rectorigin = p; }

}