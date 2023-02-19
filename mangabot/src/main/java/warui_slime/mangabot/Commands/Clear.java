package warui_slime.mangabot.Commands;

import java.awt.Color;
import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import warui_slime.mangabot.Mangabot;

public class Clear extends ListenerAdapter
{
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if(args[0].equalsIgnoreCase(Mangabot.prefix+"clear"))
        {
            if(args.length<2)
            {
            }
            else
            {
                try
                {
                    List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])+1).complete();
                    for(Message i:messages)
                    {
                        event.getChannel().deleteMessageById(i.getId()).queue();
                    }
                    EmbedBuilder success = new EmbedBuilder();
                    success.setColor(Color.green);
                    success.setTitle("✅ Successfully Deleted "+args[1]+" msgs!");
                    event.getChannel().sendMessageEmbeds(success.build()).queue();
                       
                }
                catch (IllegalArgumentException e)
                {
                    if(e.toString().contains("between 1 and 100"))
                    {
                        EmbedBuilder err = new EmbedBuilder();
                        err.setColor(0xff3923);
                        err.setTitle("Chill!...can't delete that many messages");
                        err.setDescription("Upto 100 msgs can be deleted at a time");
                        event.getChannel().sendMessageEmbeds(err.build()).queue();
                        err.clear();
                    }
                    else
                    {
                    }
                }
                

            }
        }
    }
}
