package file.explorer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Tahsina
 */
public class FileExplorer extends Application {
     private ImageView backgroundImg;
     private Button createButton;//for creating a directory
     private Button createFileButton;
     private Button exploreButton;
     private Button copyButton;
     private Button cutButton;
     private Button removeButton;
     private Button deleteButton;
     private Button renameButton;
     public void setBackgroundImg(ImageView backgroundImg) {
       this.backgroundImg = backgroundImg;
    }
    public void setCreateButton(Button createButton) {
        this.createButton = createButton;
    }
    public void setCreateFileButton(Button createFileButton){
        this.createFileButton=createFileButton;
    }
    public void setExploreButton(Button exploreButton) {
        this.exploreButton = exploreButton;
    }
    public void setCopyButton(Button copyButton) {
        this.copyButton = copyButton;
    }
    public void setCutButton(Button cutButton) {
        this.cutButton = cutButton;
    }
    public void setRemoveButton(Button removeButton) {
        this.removeButton = removeButton;
    }
    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }
    public void setRenameButton(Button renameButton) {
        this.renameButton = renameButton;
    }
    public void listf(String directoryName) {
    File directory = new File(directoryName);
    int count=0;
    // Get all files from a directory.
    File[] fList = directory.listFiles();
    if(fList != null)
        for (File file : fList) {      
            if (file.isFile()) {
                System.out.println("|->"+file.getName());
            } else if (file.isDirectory()) {
                System.out.println("DIRNAME:"+file.getName());
                listf(file.getAbsolutePath());
            }
        }
}
    
    public static void copyFile(String original,String copy) {
        File file = new File(original);
        File opFile = new File(copy);
        
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        
        try{
            fileInputStream = new FileInputStream(file);
            fileOutputStream = new FileOutputStream(opFile);
        } catch (FileNotFoundException ex) {
             Logger.getLogger(FileExplorer.class.getName()).log(Level.SEVERE, null, ex);
         }
        int i=0;
        try{
            while((i=fileInputStream.read())!=-1){
                fileOutputStream.write(i);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        finally{
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(FileExplorer.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
            if(fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(FileExplorer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void setupGame(String filePath) { 
        InputStream music;
        try {
            music = new FileInputStream(new File(filePath));//adding music file
            AudioStream audios = new AudioStream(music);
            AudioPlayer.player.start(audios);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        }

    }

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        Text text=new Text(); //new text object is created
   text.setText(" FILE ");//here the text which will be displayed is passed as a parameter
   text.setX(200);//here the coordinate of x axis is passed as a parameter
   text.setY(110);//here the coordinate of y axis is passed as a parameter
   text.setFill(Color.WHEAT);//here the color of the text is passed as parameter
   text.setStroke(Color.BLACK);//here the parameter is the border color of the text
   text.setStrokeWidth(1);//here the parameter is the border width.
   text.setFont(Font.font("abc", FontWeight.BOLD,FontPosture.ITALIC, 40));//the specification of the font of the text is passed as a parameter
   
   Text text1=new Text(); //new text object is created
   text1.setText(" EXPLORER ");//here the text which will be displayed is passed as a parameter
   text1.setX(300);//here the coordinate of x axis is passed as a parameter
   text1.setY(110);//here the coordinate of y axis is passed as a parameter
   text1.setFill(Color.WHEAT);//here the color of the text is passed as parameter
   text1.setStroke(Color.BLACK);//here the parameter is the border color of the text
   text1.setStrokeWidth(1);//here the parameter is the border width.
   text1.setFont(Font.font("abc", FontWeight.BOLD,FontPosture.ITALIC, 40));
        
        Image image;
        try{
            image = new Image(new FileInputStream("C:\\Users\\Admin\\Documents\\NetBeansProjects\\File Explorer\\src\\Home.gif")); //path of the image is given here for first scene;

            this.backgroundImg = new ImageView(image);

            backgroundImg.setFitHeight(650);
            backgroundImg.setFitWidth(700); 
            
             String styles =
         "-fx-background-radius:100;"
         +"-fx-background-color:LIGHTBLUE"
         ;//here the styles of the button is added
             
            this.createFileButton=new Button("Create a new file");
            createFileButton.setLayoutX(160);
            createFileButton.setLayoutY(210);
            
            createFileButton.setStyle(styles);//the style is passed as a parameter
            
            createFileButton.setOnMouseClicked(event ->{
                try{
                 JFrame frame=new JFrame(); //creating JFrame
    
      String path=JOptionPane.showInputDialog(frame,"Please provide the path where you want to create a new file:");
      String name=JOptionPane.showInputDialog(frame,"Enter your what you want to name your file with extension:");
                System.out.println(path);
                System.out.println(name);
                //C:/Users/Admin/Desktop
                File file = new File(path+"/"+name);
                file.createNewFile();
                    System.out.println("file is created!");
             }catch(Exception e){
                 JOptionPane.showMessageDialog(null, "The path you have entered does not exist");
             }
            });
            
            this.createButton=new Button("Create a new folder");
            createButton.setLayoutX(400);
            createButton.setLayoutY(210);
           
            createButton.setStyle(styles);//the style is passed as a parameter
            
            createButton.setOnMouseClicked(event -> {
             try{
                 JFrame frame=new JFrame(); //creating JFrame
    
      String path=JOptionPane.showInputDialog(frame,"Please provide the path where you want to create a new folder:");
      String name=JOptionPane.showInputDialog(frame,"GIVE YOUR FOLDER A NAME:");
                System.out.println(path);
                System.out.println(name);
                //C:/Users/Admin/Desktop
                File dir = new File(path+"/"+name);
                dir.mkdir();
                 System.out.println("folder is created");
             }catch(Exception e){
                 JOptionPane.showMessageDialog(null, "The path you have entered does not exist");
             }
 
        });
            
            this.exploreButton=new Button("Explore");
            exploreButton.setLayoutX(310);
            exploreButton.setLayoutY(170);
          
         ;//here the styles of the button is added
            exploreButton.setStyle(styles);//the style is passed as a parameter
            
            exploreButton.setOnMouseClicked(event->{
                try{
                     JFrame frame=new JFrame(); //creating JFrame
    
                  String path=JOptionPane.showInputDialog(frame,"Please provide the absolute path of the directory you want to explore:");
      
                //C:/Users/Admin/Desktop
                File dir = new File(path);
                listf(path);
                    
                }catch(Exception e){
                    System.out.println("the path you entered is wrong");
                }
            });
             
            this.copyButton=new Button("Copy");
            copyButton.setLayoutX(260);
            copyButton.setLayoutY(340);
           
         ;//here the styles of the button is added
            copyButton.setStyle(styles);//the style is passed as a parameter
            
            copyButton.setOnMouseClicked(event->{
                 try{
                     JFrame frame=new JFrame(); //creating JFrame
    
                  String path=JOptionPane.showInputDialog(frame,"Please provide the absolute path of the file you want to copy:");
                   String input=JOptionPane.showInputDialog(frame,"Please provide the absolute path of the file where you want to copy:");
                //C:/Users/Admin/Desktop
                
                   copyFile(path,input); 
                }catch(Exception e){
                    System.out.println("the path you entered is wrong");
                }
            });
            
            this.cutButton=new Button("Cut");
            cutButton.setLayoutX(390);
            cutButton.setLayoutY(390);
           
         ;//here the styles of the button is added
            cutButton.setStyle(styles);//the style is passed as a parameter
            
              cutButton.setOnMouseClicked(event->{
                 try{
                     JFrame frame=new JFrame(); //creating JFrame
    
                  String path=JOptionPane.showInputDialog(frame,"Please provide the absolute path of the file you want to cut:");
                   String input=JOptionPane.showInputDialog(frame,"Please provide the absolute path of the file where you want to copy:");
                //C:/Users/Admin/Desktop
                
                   copyFile(path,input); 
                   File dir = new File(path);
                   dir.delete();
                }catch(Exception e){
                    System.out.println("the path you entered is wrong");
                }
            });
            
             this.removeButton=new Button("Remove");
            removeButton.setLayoutX(330);
            removeButton.setLayoutY(370);
           
         //here the styles of the button is added
            removeButton.setStyle(styles);
            
            this.deleteButton=new Button("Remove");
            deleteButton.setLayoutX(190);
            deleteButton.setLayoutY(290);
            
         //here the styles of the button is added
            deleteButton.setStyle(styles);
            deleteButton.setOnMouseClicked(event->{
            try{
                 JFrame frame=new JFrame(); //creating JFrame
    
      String path=JOptionPane.showInputDialog(frame,"Please provide the absolute path of the file/folder you want to delete");
      
                //C:/Users/Admin/Desktop
                File dir = new File(path);
                dir.delete();
                System.out.println("folder/file is deleted");
             }catch(Exception e){
                 JOptionPane.showMessageDialog(null, "The path you have entered does not exist");
             }
        });
            
            this.renameButton=new Button("Rename");
            renameButton.setLayoutX(410);
            renameButton.setLayoutY(290);
           
         //here the styles of the button is added
            renameButton.setStyle(styles);
            
            renameButton.setOnMouseClicked(event->{
                 try{
                     JFrame frame=new JFrame(); //creating JFrame
    
                  String path=JOptionPane.showInputDialog(frame,"Please provide the absolute path of the directory you want to explore:");
                   String input=JOptionPane.showInputDialog(frame,"Please provide the name you want to give to your file:");
                //C:/Users/Admin/Desktop
                File dir = new File(path);
                File rename = new File(dir.getParent()+"/"+input);
                boolean flag = dir.renameTo(rename);
                if(flag==true){
                    System.out.println("File Successfully Renamed");
                }
                else{
                    System.out.println("Some Error Occured");
                }
                    
                }catch(Exception e){
                    System.out.println("the path you entered is wrong");
                }
            });
            
         
        }catch(FileNotFoundException ex){
            System.out.println("file not found");
            
        }
        Group root = new Group();
        root.getChildren().add(backgroundImg);
        root.getChildren().add(text);
        root.getChildren().add(text1);
        root.getChildren().add(createFileButton);
        root.getChildren().add(createButton);
        root.getChildren().add(exploreButton);
        root.getChildren().add(copyButton);
        root.getChildren().add(cutButton);
        //root.getChildren().add (removeButton);
        root.getChildren().add(deleteButton);
        root.getChildren().add(renameButton);
        //root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 700, 650);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        setupGame("C:\\Users\\Admin\\Documents\\NetBeansProjects\\File Explorer\\src\\Intro.wav");
        launch(args);
    }
    
}
