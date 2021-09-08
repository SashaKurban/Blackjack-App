//Oleksandra Kurbanova

/*
 * class is used to process the card images and create an array of all card images
 * each card image is assigned to a matching card based on its index in the array
 */

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class bfImage {
	
	protected BufferedImage[] cardImg;
	
	public bfImage() 
	{
		try {
			//read in the main image with all cards
			BufferedImage bigImg = ImageIO.read(new File("cardsImg.png"));
			//set size of a single card
			final int width = 73;
			final int height = 98;
			//set number of rows and columns on the initial card image
			final int rows = 4;
			final int cols = 13;
			//create an array of card images 
			cardImg = new BufferedImage[rows * cols];

			//fill the array with card images matching their indexes to the card values in the deck
			for (int i = 0; i < rows; i++)
			{
			    for (int j = 0; j < cols; j++)
			    {
			    	cardImg[(i * cols) + j] = bigImg.getSubimage(j * width, i * height, width,height);
			    }
			}
		}
		//if card image not found, an exception message is printed
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
}
