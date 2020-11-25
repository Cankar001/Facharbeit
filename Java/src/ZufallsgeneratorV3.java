package zufallsgeneratorPC;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

public class ZufallsgeneratorV3
	{
	static int[][] rgb;
	static boolean[][] bitmap;
	
	static String location;
	static int counter;
	
	static BufferedImage img;
	static File file;
	
	int grenze;
	
	public ZufallsgeneratorV3()
		{
		rgb = new int[1000][1000];
		bitmap = new boolean[1000][1000];
		
		file = null;
		counter = 0;
		grenze = 124;
		}
	
	public void setRGB()
		{
		for ( int y = 0; y < 100; y++ )
			{
			for ( int x = 0; x < 100; x++ )
				{
				Color c = new Color(img.getRGB(x, y));
				
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				
				int erg = (red + green + blue) / 3;
				rgb[x][y] = erg;
				}
			}
		}
	
	public void setBitmap()
		{
		for ( int y = 0; y < 100; y++ )
			{
			for ( int x = 0; x < 100; x++ )
				{
				if ( rgb[x][y] >= grenze )
					{
					bitmap[x][y] = false;
					}
				else
					{
					bitmap[x][y] = true;
					}
				}
			}
		}
	
	public void remove(int x, int y)
		{
		bitmap[x][y] = false;
		
		if ( x<100 && y<100 && bitmap[x+1][y+1] ) remove(x+1,y+1); // Oben rechts
		if ( x>  0 && y>  0 && bitmap[x-1][y-1] ) remove(x-1,y-1); // Unten Links
		if ( x<100 && y>  0 && bitmap[x+1][y-1] ) remove(x+1,y-1); // Unten Rechts
		if ( x>  0 && y<100 && bitmap[x-1][y+1] ) remove(x-1,y+1); // Oben Links
		if ( x<100 &&          bitmap[x+1][y  ] ) remove(x+1,y);   // Rechts
		if ( y<100 &&          bitmap[x  ][y+1] ) remove(x,y+1);   // Oben
		if ( y>  0 &&          bitmap[x  ][y-1] ) remove(x,y-1);   // Unten
		if ( x>  0 &&          bitmap[x-1][y  ] ) remove(x-1,y);   // Links
		}
	
	public int count()
		{
		int n = 0;
		//bitmapDrucken();
		for ( int y = 1; y < 99; y++ )
			{
			for ( int x = 1; x < 99; x++ )
				{
				if ( bitmap[x][y] )
					{
					n++;
					remove(x,y);
					//System.out.println("X = " + x + " Y = " + y);
					//bitmapDrucken();
					}
				}
			}
		
		//System.out.println("N = " + n);
		return n;
		}
	
	public void checkFile()
		{
		if ( file == null )
			{
			System.out.println("Fehler: Datei konnte nicht geladen werden!");
			System.exit(1);
			}
		}
	
	public void setImage(File pFile)
		{
		try
			{
			img = ImageIO.read(pFile);
			}
		catch ( IOException e )
			{
			e.printStackTrace();
			}
		}
	
	public void ErgebnisAusgeben(int pCounter)
		{
		if ( pCounter == 0 )
			{
			System.out.println("Fehler: Die Pixel konnten nicht gezaehlt werden!");
			}
		else
			{
			if ( pCounter == 1 )
				{
				System.out.println("Ergebnis: Es befindet sich ein schwarzer Punkt auf dem Bildschirm.");
				System.out.println("--------------------------------------------------------");
				}
			else
				{
				System.out.println("Ergebnis: Es befinden sich " + pCounter + " Punkte auf dem Bildschirm.");
				System.out.println("--------------------------------------------------------");
				}
			}
		}
	
	public void rgbDrucken()
		{
		for ( int y = 100; y > 0; y-- )
			{
			for ( int x = 0; x < 100; x++ )
				{
				if ( rgb[x][y] == 0 )
					{
					System.out.print("0");
					}
				else
					{
					System.out.print("1");
					}
				}
			System.out.println();
			}
		}
	
	public void bitmapDrucken()
		{
		
		for ( int y = 100; y > 0; y-- )
			{
			for ( int x = 0; x < 100; x++ )
				{
				if ( bitmap[x][y] == true )
					{
					System.out.print("O");
					}
				else
					{
					System.out.print(".");
					}
				}
			System.out.println();
			}
		System.out.println();
	    
		try
			{
			TimeUnit.SECONDS.sleep(1);
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		}
	
	public void rgbWerteDrucken()
		{
		for ( int y = 100; y > 0; y-- )
			{
			for ( int x = 0; x < 100; x++ )
				{
				System.out.printf("%03d",rgb[x][y]);
				System.out.print(" ");
				}
			System.out.println();
			}
		}
	
	public void bitmapWerteDrucken()
		{
		for ( int y = 100; y > 0; y-- )
			{
			for ( int x = 0; x < 100; x++ )
				{
				System.out.printf("%03d",bitmap[x][y]);
				System.out.print(" ");
				}
			System.out.println();
			}
		}
	
	public int testeBild(String datName)
		{
		// Datei erstellen
		file = new File(datName);
		checkFile();
		
		// Datei als Bild laden zur Farberkennung
		setImage(file);
		
		// RGB-Farbraum ermitteln
		setRGB();
		
		// Bitmap generieren
		setBitmap();

		return count();
		}
	
	public void RegressionsTest()
		{
		System.out.println("Regressionstest: ");
		for ( int t = 1; t <= 6; t++ )
			{
			if ( t != testeBild("../../beispielbilder/100x100/"+t+".png") )
				{
				System.err.println("Fehler bei Bild "+t+"! \n");
				System.exit(1);
				}
			else
				{
				System.out.println("   Test " + t + " OK.");
				}
			}
		}
	
	public static void main(String[] args)
		{
		ZufallsgeneratorV3 programm = new ZufallsgeneratorV3();
		
		programm.RegressionsTest();
		
		int counter = programm.testeBild("../../beispielbilder/100x100/6.png"); // Beispiel Location
		
		System.out.println();
		programm.ErgebnisAusgeben(counter);
		}
	}
