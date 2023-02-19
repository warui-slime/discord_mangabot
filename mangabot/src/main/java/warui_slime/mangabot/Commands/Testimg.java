package warui_slime.mangabot.Commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import warui_slime.mangabot.Mangabot;

public class Testimg extends ListenerAdapter
{
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if(args[0].equalsIgnoreCase(Mangabot.prefix+"sampleimg"))
        {

            event.getChannel().sendMessage("https://www.toonix.xyz/cdn_mangaraw/tokyo-manji-revengers-comic/chapter-268/16.jpg").queue();
        }
    }
}
