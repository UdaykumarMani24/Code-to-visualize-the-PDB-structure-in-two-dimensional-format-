 
/* <applet code="visual" width=250 height=250> </applet> */


import java.io.*;
import java.awt.*;
import javax.swing.*;
//import java.applet.*;

public class visual extends JApplet 
{public String fileName;
public void paint(Graphics g)
{
	fileName = "1crn.pdb";
	visrep(g,fileName);}
void visrep(Graphics g,String fileName){
//String fileName = "1crn.pdb";
String line = null;
 int count=0;
 int i=0;
  int NoOfAa=0; 
  int NoOfAtoms=0;
  int argus=0;
try {
   FileReader fileReader =new FileReader(fileName);
   BufferedReader bufferedReader =new BufferedReader(fileReader);
    while((line = bufferedReader.readLine()) != null) {
    if(line.substring(0,4).equals("ATOM"))
	{count++; }}
    fileReader.close();   
	}
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
		catch(IOException ex)
		{
		System.out.println("Error in reading file"+fileName);
		}
		String backbone[]=new String[count];
		String alldetails[]=new String[count];
       try{
	      FileReader fileReader =new FileReader(fileName);
   BufferedReader bufferedReader =new BufferedReader(fileReader);
   int n=0;
    while((line = bufferedReader.readLine()) != null) {
	if(line.substring(0,4).equals("ATOM"))
	{alldetails[n]=line;n++;
	if(line.substring(13,15).equals("N ")||line.substring(13,15).equals("CA")||line.substring(13,15).equals("C "))
	{backbone[i]=line;
	//System.out.println(line);
	i++;
	}
     }
	 }
	  NoOfAtoms=n-1;
	 	 NoOfAa=i/3;
	  fileReader.close();
	   	   }
	   catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
		catch(IOException ex)
		{
		System.out.println("Error in reading file"+fileName);
		}
	   int sizeofbb=i;
	   	   	   double coordDetails[][][]=new double[NoOfAa][3][3];
      int bb=0;
	   for(i=0;i<NoOfAa;i++)
	   {for(int j=0;j<3;j++)
		   {
			  coordDetails[i][j][0]=Double.parseDouble(backbone[bb].substring(31,38));
			   coordDetails[i][j][1]=Double.parseDouble(backbone[bb].substring(40,47));
			   coordDetails[i][j][2]=Double.parseDouble(backbone[bb].substring(48,56));bb++;
	   }}
	   double centroid[][]=new double[NoOfAa][3];
	   	   int  cent[][]=new int[NoOfAa][3];

	   for(i=0;i<NoOfAa;i++)
	   {for(int j=0;j<3;j++)
	  {for(int k=0;k<3;k++)
	   {centroid[i][k]+=coordDetails[i][j][k];
		  }
		   }
		   }
	   for(i=0;i<NoOfAa;i++)
	   {for(int j=0;j<3;j++)
	   {centroid[i][j]/=3;}}
	    for(i=0;i<NoOfAa;i++)
	   {for(int j=0;j<3;j++)
	   {cent[i][j]=(int)(centroid[i][j]);}
	   }
	int no=-1;
		  double atomwisedetails[][][]=new double[NoOfAa][20][3];
	   for(i=0;i<NoOfAa-1;i++)
	   {
	   g.drawLine(cent[i][0]*10,cent[i][1]*10,cent[i+1][0]*10,cent[i+1][1]*10);
	   }
	   for(i=0;i<NoOfAtoms;)
	   {
	   if((alldetails[i].substring(13,15)).equals("N "))
	   {i=i+3;no++;int k=0;
	   try
	   {
	   while(!((alldetails[i].substring(13,15)).equals("N ")) && i<NoOfAtoms && k<20)
	   {atomwisedetails[no][k][0]=Double.parseDouble(alldetails[i].substring(31,38));
	  
	   			   atomwisedetails[no][k][1]=Double.parseDouble(alldetails[i].substring(40,47));
			   atomwisedetails[no][k][2]=Double.parseDouble(alldetails[i].substring(48,56));
			 // System.out.println(atomwisedetails[no][k][0]+" "+atomwisedetails[no][k][1]+" "+atomwisedetails[no][k][2]);
	   k++;i++;}
	   }
	   catch(Exception e)
	   {
	   System.out.println(e.getMessage());
	   System.out.println("Error");
	   }
	   }
	   
	   else
	   {i++;}
	   }
	   double sidecent[][]=new double[NoOfAa][3];
	   for(i=0;i<NoOfAa;i++)
	   {
	   for(int k=0;k<20;k++)
	   {
	   for(int m=0;m<3;m++)
	   {
	   sidecent[i][m]+=atomwisedetails[i][k][m];
	   }
	   }
	   count=0;
	   for(int l=0;l<20;l++)
	   {
	   if(atomwisedetails[i][l][0]!=0.0)
	   {count++;}
	   }
	   for(int q=0;q<3;q++)
	   {
	    sidecent[i][q]/=count;}
	   }
	   int sidecentroid[][]=new int[NoOfAa][3];
	   for(i=0;i<NoOfAa;i++)
	   {
	   for(int j=0;j<3;j++)
	   {
	  
	   sidecentroid[i][j]=(int)sidecent[i][j];
	   }
	   }
	/*   for(i=0;i<NoOfAa;i++)
	   {
	   for(int j=0;j<3;j++)
	   {System.out.println(sidecentroid[i][j]);}}*/
	   for(i=0;i<NoOfAa;i++)
	   {g.setColor(Color.BLUE);
	   g.drawLine(cent[i][0]*10,cent[i][1]*10,sidecentroid[i][0]*10,sidecentroid[i][1]*10);
	   }
	    for(i=0;i<NoOfAa;i++)
	    {
			g.setColor(Color.RED);
			g.fillOval(cent[i][0]*10-5,cent[i][1]*10-5,10,10);}
			 for(i=0;i<NoOfAa;i++)
	    {
			g.setColor(Color.GREEN);
			g.fillOval(sidecentroid[i][0]*10-5,sidecentroid[i][1]*10-5,10,10);}
			
	     
	    }//paint 
	   
	   } //class
	   
	  // <Applet code="visual.class" width=700 height=700>
	  // </Applet>
	   