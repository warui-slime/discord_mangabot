package warui_slime.mangabot.Commands;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;

import java.io.InputStream;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;


import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;
import warui_slime.mangabot.Mangabot;

public class Jjk extends ListenerAdapter
{
    public static int pg=1;
    public static int ch;
    public static int num_pages;
    public static int max_ch = 200;

    public String[] jjkchap(int ch) throws Exception
    {
        
        Document doc = Jsoup.connect("https://www.jujustukaisen.com/manga/jujutsu-kaisen-chapter-"+ch+"/").get();
        Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g)]");
        String[] page_links = new String[images.size()];
        for(int i =0;i<images.size();i++)
        {
            page_links[i]=images.get(i).attr("src").toString();
        }
        num_pages = page_links.length;  
        return page_links;
        
           
    }

    public void jjkpagemaker(MessageChannelUnion chan,String[] pages)
    {
        EmbedBuilder send_ch = new EmbedBuilder();
        send_ch.setColor(Color.green);
        send_ch.setTitle("Jujutsu Kaisen Chapter "+ch);
        send_ch.setImage(pages[pg-1]);
        chan.sendMessageEmbeds(send_ch.build()).complete().addReaction(Emoji.fromUnicode("⏮️")).queue();
        send_ch.clear();
        

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if(args[0].equalsIgnoreCase(Mangabot.prefix+"jjk") && args.length>1)
        {
            if(Integer.parseInt(args[1])<=max_ch)
            {
                   
                try
                {
                    ch = Integer.parseInt(args[1]);
                    Mangabot.curr_manga = "JJK";
                    Mangabot.msguser = event.getAuthor();
                    jjkpagemaker(event.getChannel(),jjkchap(ch));
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
 
            }
            else
            {
                EmbedBuilder send_ch = new EmbedBuilder();
                send_ch.setColor(Color.green);
                send_ch.setTitle("Manga is still on Chapter "+max_ch);
                event.getChannel().sendMessageEmbeds(send_ch.build()).queue();
                send_ch.clear();
            }
        }

        
    }
    public void next_page(MessageReactionAddEvent event)
    {
        
        try
        {
            if(num_pages>pg)
            {
                event.getChannel().deleteMessageById(event.getMessageId()).queue();
                pg++;
                jjkpagemaker(event.getChannel(),jjkchap(ch));
            }
            
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void prev_page(MessageReactionAddEvent event)
    {
        if(pg>1)
        {
            event.getChannel().deleteMessageById(event.getMessageId()).queue();
            pg--;
            try
            {
                jjkpagemaker(event.getChannel(),jjkchap(ch));
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
    }
    public void prev_chap(MessageReactionAddEvent event)
    {
        
        if(ch>1)
        {
            event.getChannel().deleteMessageById(event.getMessageId()).queue();
            ch--;
            pg = 1;
            try
            {
                jjkpagemaker(event.getChannel(),jjkchap(ch));
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
    }
    public void next_chap(MessageReactionAddEvent event)
    {
        if(ch<max_ch)
        {
            event.getChannel().deleteMessageById(event.getMessageId()).queue();
            ch++;
            pg = 1;
            try
            {
                jjkpagemaker(event.getChannel(),jjkchap(ch));
            }
            catch (Exception e)
            {
                System.out.println(e);
            }   
        }
        else
        {
            event.getChannel().deleteMessageById(event.getMessageId()).queue();
            EmbedBuilder send_ch = new EmbedBuilder();
            File file = new File("Clam.jpg");
            try{
                InputStream x = new FileInputStream(file);
                send_ch.setColor(Color.green);
                send_ch.setTitle("Be Clam");
                send_ch.setImage("attachment://Clam.jpg");
                event.getChannel().sendFiles(FileUpload.fromData(x,"Clam.jpg")).setEmbeds(send_ch.build()).queue();
                send_ch.clear();
                }
            catch(Exception e)
            {
                System.out.println(e);
            }    

            
                
        }
    }   
}
