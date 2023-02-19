package warui_slime.mangabot.Events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class Memes extends ListenerAdapter
{
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if(!event.getMessage().getContentRaw().matches("((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)") && event.getMessage().getContentRaw().toLowerCase().contains("speed"))
        {
            event.getChannel().sendMessage("https://tenor.com/view/speed-i-am-speed-lightning-mcqueen-cars-meme-gif-14031708").complete();
        }   
    }
}
