package zufallsgeneratorPC;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Zufallsgenerator
	{
	static int[][] rgb;
	static boolean[][] bitmap;
	
	static String location;
	static int counter;
	
	static BufferedImage img;
	static File file;
	
	int grenze, x, y;
	
	public Zufallsgenerator()
		{
		rgb = new int[1000][1000];
		bitmap = new boolean[1000][1000];
		file = null;
		counter = 0;
		grenze = 128;
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
	
	public int xRichtung()
		{
		// In X-Richtung Array durchlaufen
		int entfernt = 0;
		boolean hatEinNachbar, hatZweiNachbar, hatDreiNachbar;
		
		for ( int y = 99; y > 0; y-- )
			{
			for ( int x = 1; x < 100; x++ ) 
				{
			//	entfernt = 0;
				// Auf beiden Seiten ein Nachbar
				hatEinNachbar = (bitmap[x+1][y]);
				hatZweiNachbar = (bitmap[x+2][y]);
				hatDreiNachbar = (bitmap[x+3][y]);
				
				if ( bitmap[x][y] && hatEinNachbar && hatZweiNachbar && hatDreiNachbar )
					{
					bitmap[x+3][y] = false;
					//System.out.println("bitmap: " + bitmap[x][y] + " wurde entfernt");
					entfernt++;
					}
				else if ( bitmap[x][y] && hatEinNachbar && hatZweiNachbar)
					{
					bitmap[x+2][y] = false;
					//System.out.println("bitmap: " + bitmap[x][y] + " wurde entfernt");
					entfernt++;
					}
				else if ( bitmap[x][y] && hatEinNachbar )
					{
					bitmap[x+1][y] = false;
					//System.out.println("bitmap: " + bitmap[x][y] + " wurde entfernt");
					entfernt++;
					}
				}
			}
		return entfernt;
		}
	
	public int yRichtung()
		{
		// In Y-Richtung Array durchlaufen
		int entfernt = 0;
		boolean hatEinNachbar, hatZweiNachbar, hatDreiNachbar;
		
		for ( int x = 1; x < 100; x++ )
			{
			for ( int y = 99; y > 0; y--  ) 
				{
				// Auf beiden Seiten ein Nachbar
				hatEinNachbar = (bitmap[x][y+1]);
				hatZweiNachbar = (bitmap[x][y+2]);
				hatDreiNachbar = (bitmap[x][y+3]);
				
				if ( bitmap[x][y] && hatEinNachbar && hatZweiNachbar && hatDreiNachbar )
					{
					bitmap[x][y+3] = false;
					//System.out.println("bitmap: " + bitmap[x][y] + " wurde entfernt");
					entfernt++;
					}
				else if ( bitmap[x][y] && hatEinNachbar && hatZweiNachbar)
					{
					bitmap[x][y+2] = false;
					//System.out.println("bitmap: " + bitmap[x][y] + " wurde entfernt");
					entfernt++;
					}
				else if ( bitmap[x][y] && hatEinNachbar )
					{
					bitmap[x][y+1] = false;
					//System.out.println("bitmap: " + bitmap[x][y] + " wurde entfernt");
					entfernt++;
					}
				}
			}
		return entfernt;
		}
	
	public int reduzieren()
		{
		int entfernt = 0;
		boolean obenLinksEins, obenRechtsEins;
		boolean obenLinksZwei, obenRechtsZwei;
		boolean obenLinks, obenRechts;
		
		for ( int y = 1; y < 100; y++ )
			{
			for ( int x = 99; x > 2; x-- )
				{
				obenLinksEins = (bitmap[x-1][y+1]);
				obenRechtsEins = (bitmap[x+1][y+1]);
				
				obenLinksZwei = (bitmap[x-2][y+2]);
				obenRechtsZwei = (bitmap[x+2][y+2]);
				
				obenLinks = (bitmap[x-2][y+1]);
				obenRechts = (bitmap[x+2][y+1]);
				
				if ( bitmap[x][y] && obenLinks )
					{
					bitmap[x-2][y+1] = false;
					entfernt++;
					}
				else if ( bitmap[x][y] && obenRechts )
					{
					bitmap[x+2][y+1] = false;
					entfernt++;
					}
				
				if ( bitmap[x][y] && obenLinksZwei )
					{
					bitmap[x-2][y+2] = false;
					entfernt++;
					}
				else if ( bitmap[x][y] && obenRechtsZwei )
					{
					bitmap[x+2][y+2] = false;
					entfernt++;
					}
				
				if ( bitmap[x][y] && obenLinksEins )
					{
					bitmap[x-1][y+1] = false;
					entfernt++;
					}
				else if ( bitmap[x][y] && obenRechtsEins )
					{
					bitmap[x+1][y+1] = false;
					entfernt++;
					}
				}
			}
		
		return entfernt;
		}
	
	public void shrink()
		{
		
		// Schwarzen oberen Rand entfernen
		for ( int a = 0; a < 100; a++ ) bitmap[a][100] = false;
		
		// Grobes entfernen der überflüssigen Pixel
		while ( (y = yRichtung()) != 0 && (x = xRichtung()) != 0 )
			{
			System.out.println();
			System.out.println(x + " Pixel wurden in X-Richtung entfernt.");
			System.out.println(y + " Pixel wurden in y-Richtung entfernt.");
			System.out.println();
			}
		
		System.out.println();
		
		System.out.println("Bitmapbild nach XY");
		bitmapDrucken();
		x = 0;
		y = 0;
		
		int red = 0;
		while ( (red = reduzieren()) != 0 )
			{
			System.out.println();
			System.out.println(red + " Pixel wurden entfernt.");
			System.out.println();
			}
		
		}
	
	public void checkFile()
		{
		if ( file != null )
			{
			System.out.println("ok: Datei '" + file.getName() + "' erfolgreich geladen!" );
			}
		else
			{
			System.out.println("Fehler: Datei konnte nicht geladen werden!");
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
	
	public int count()
		{
		int count = 0;
		for ( int y = 100; y > 0; y-- )
			{
			for ( int x = 0; x < 100; x++ )
				{
				if ( bitmap[x][y] )
					{
					count++;
					}
				}
			}
		return count;
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
				}
			else
				{
				System.out.println("Ergebnis: Es befinden sich " + pCounter + " Punkte auf dem Bildschirm.");
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
	
	public static void main(String[] args)
		{
		Zufallsgenerator programm = new Zufallsgenerator();
		
		// location bestimmen
		location = "./beispielbilder/100x100/2.png"; // Beispiel Location
		//location = ""; // Richtige Location
		
		// Datei erstellen
		file = new File(location);
		programm.checkFile();
		
		// Datei als Bild laden zur Farberkennung
		programm.setImage(file);
		
		// RGB-Farbraum ermitteln
		programm.setRGB();
		
		// Bitmap generieren
		programm.setBitmap();
		
		// RGB Farbwerte angeben
		System.out.println();
		System.out.println("Info: RGB-Farbwerte");
		programm.rgbWerteDrucken();
		
		// Bitmap Bild drucken
		System.out.println();
		System.out.println("Info: Bitmapbild");
		programm.bitmapDrucken();
		
		// Pixel reduzieren
		programm.shrink();
		
		// Bitmap Bild erneut drucken
		System.out.println();
		System.out.println("Info: Bitmapbild Final");
		programm.bitmapDrucken();
		
		// Pixel zählen
		counter = programm.count();
		
		// Ergbenis ausgeben
		programm.ErgebnisAusgeben(counter);
		
		
		/*
		 * GesamtPixelWerte:
		 * 
		 * 1.png = 0537 Pixel
		 * 2.png = 1073 Pixel
		 * 3.png = 1610 Pixel
		 * 4.png = 2146 Pixel
		 * 5.png = 2683 Pixel
		 * 6.png = 3219 Pixel
		 */
		}
	}
