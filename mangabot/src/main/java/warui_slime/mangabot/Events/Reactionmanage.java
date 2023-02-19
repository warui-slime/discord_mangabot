package warui_slime.mangabot.Events;


import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import warui_slime.mangabot.Mangabot;
import warui_slime.mangabot.Commands.Jjk;
import warui_slime.mangabot.Commands.Tokyorevengers;

public class Reactionmanage extends ListenerAdapter
{
    Tokyorevengers Trobj = new Tokyorevengers();
    Jjk jjkobj = new Jjk();
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event)
    {
        
        if(event.getEmoji().equals(Emoji.fromUnicode("⏮️")) && event.getUser().isBot())
        {
            event.getChannel().addReactionById(event.getMessageId(), Emoji.fromUnicode("⬅️")).queue();
            event.getChannel().addReactionById(event.getMessageId(), Emoji.fromUnicode("➡️")).queue();
            event.getChannel().addReactionById(event.getMessageId(), Emoji.fromUnicode("⏭️")).queue();
        }
        else if(event.getEmoji().equals(Emoji.fromUnicode("⏮️")) && event.getUser()==Mangabot.msguser)
        {
            if(Mangabot.curr_manga == "TR")
            {
                Trobj.prev_chap(event);
            }
            else if(Mangabot.curr_manga == "JJK")
            {
                jjkobj.prev_chap(event);
            }   
        }
        else if(event.getEmoji().equals(Emoji.fromUnicode("⬅️")) && event.getUser()==Mangabot.msguser)
        {
            if(Mangabot.curr_manga == "TR")
            {
                Trobj.prev_page(event);
            }
            else if(Mangabot.curr_manga == "JJK")
            {
                jjkobj.prev_page(event);
            }   
        }
        else if(event.getEmoji().equals(Emoji.fromUnicode("➡️")) && event.getUser()==Mangabot.msguser)
        {
            if(Mangabot.curr_manga == "TR")
            {
                Trobj.next_page(event);
            }
            else if(Mangabot.curr_manga == "JJK")
            {
                jjkobj.next_page(event);
            }
        }
        else if(event.getEmoji().equals(Emoji.fromUnicode("⏭️")) && event.getUser()==Mangabot.msguser)
        {
            if(Mangabot.curr_manga == "TR")
            {
                Trobj.next_chap(event);
            }
            else if(Mangabot.curr_manga == "JJK")
            {
                jjkobj.next_chap(event);
            }
        }
    }

    
    
}
