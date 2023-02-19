package warui_slime.mangabot.Commands;



import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import warui_slime.mangabot.Mangabot;

public class Pingme extends ListenerAdapter
{
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if(args[0].equalsIgnoreCase(Mangabot.prefix+"pingme"))
        {
            event.getChannel().sendMessage("<@"+event.getAuthor().getId()+">").queue();
        }   
    }      
}
