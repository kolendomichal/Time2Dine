import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Graphics2D extends JPanel {

    private BufferedImage chair = null;
    private BufferedImage table4 = null;
    private BufferedImage table6 = null;
    private BufferedImage table8 = null;
    private BufferedImage lamp = null;
    private BufferedImage bench = null;

    private BufferedImage savePicture;

    public Graphics2D() {
        this.setVisible(true);

        try {
            chair = ImageIO.read(new File("Time2Dine\\src\\ComponentsImage\\chair.png"));
            table4 = ImageIO.read(new File("Time2Dine\\src\\ComponentsImage\\4Table.png"));
            table6 = ImageIO.read(new File("Time2Dine\\src\\ComponentsImage\\6Table.png"));
            table8 = ImageIO.read(new File("Time2Dine\\src\\ComponentsImage\\8Table.png"));
            lamp = ImageIO.read(new File("Time2Dine\\src\\ComponentsImage\\lamp.png"));
            bench = ImageIO.read(new File("Time2Dine\\src\\ComponentsImage\\benchSmall.png"));
        } catch (IOException e) {
            System.out.println("Nie znaleziono obrazka!");
        }
        this.setBounds(6, 6, 586, 506);

        savePicture = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = savePicture.createGraphics();
        this.paint(g);
        g.dispose();
        repaint();
    }

    public void visualize(Chromosome chromosome) {

    }

    @Override
    public void paintComponent(Graphics g) {
        this.setBackground(Color.WHITE);
        this.setBounds(6, 6, 586, 506);
        super.paintComponent(g);

        int point1 = 0;
        int point2 = 250;
        int point3 = 400;
        int dt = 1; //cause of line thickness, needed to fill polygon properly

        int yBorderPoly[] = {point1, point1, point2, point2, point3, point3};
        int xBorderPoly[] = {point1, point2, point2, point3, point3, point1};
        Polygon borderPoly = new Polygon(xBorderPoly, yBorderPoly, xBorderPoly.length);

        int yFilledPoly[] = {point1 + dt, point1 + dt, point2 + dt, point2 + dt, point3 - dt, point3 - dt};
        int xFilledPoly[] = {point1 + dt, point2 - dt, point2 - dt, point3 - dt, point3 - dt, point1 + dt};
        Polygon filledPoly = new Polygon(xFilledPoly, yFilledPoly, xFilledPoly.length);

        g.setColor(Color.BLACK);
        g.drawPolygon(borderPoly);
        // g.setColor(Color.YELLOW);
        //  g.fillPolygon(filledPoly);

        drawRotate(chair, 0, 27, 30, g, 1, 1);
        drawRotate(chair, 0, 52, 30, g, 1, 1);
        drawRotate(chair, 0, 77, 30, g, 1, 1);
        drawRotate(chair, 0, 102, 30, g, 1, 1);
        drawRotate(chair, 0, 127, 30, g, 1, 1);
        drawRotate(chair, 0, 152, 30, g, 1, 1);
        drawRotate(chair, 0, 177, 30, g, 1, 1);
        drawRotate(chair, 0, 202, 30, g, 1, 1);
        drawRotate(bench, 0, 23, 55, g, 1, 1);
        drawRotate(bench, 0, 121, 55, g, 1, 1);
        drawRotate(lamp, 0, 200, 200, g, 1, 1);

    }

    private static void drawRotate(BufferedImage img, double angle, int locationX, int locationY, Graphics g, double xScale, double yScale) {
        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(angle), img.getWidth() / 2, img.getHeight() / 2);
        tx.scale(xScale, yScale);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        g.drawImage(op.filter(img, null), locationX, locationY, null);
    }

    /*Błąd w rzędu  <= 0,5%Punkt startu (0,0)*/
    private static void drawCanteen(Canteen canteen, Graphics g) {
        double meterToPixel = 37.74;
        int point2 = (int) (meterToPixel * canteen.gettWall());
        int point3 = (int) ((meterToPixel * (canteen.getlWall() - canteen.getrWall())));
        int point4 = (int) (Math.abs(meterToPixel * (canteen.getbWall() - canteen.gettWall())) + point2);
        int point5 = (int) (Math.abs(meterToPixel * (canteen.getrWall())) + point3);
        int yBorderPoly[] = {0, 0, point3, point3, point5, point5};
        int xBorderPoly[] = {0, point2, point2, point4, point4, 0};
        Polygon borderPoly = new Polygon(xBorderPoly, yBorderPoly, xBorderPoly.length);
        g.setColor(Color.BLACK);
        g.drawPolygon(borderPoly);
    }


    public BufferedImage getSavePicture() {
        return savePicture;
    }
}
