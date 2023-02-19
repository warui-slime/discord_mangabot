package warui_slime.mangabot.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import warui_slime.mangabot.Mangabot;

public class Test extends ListenerAdapter
{
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if(args[0].equalsIgnoreCase(Mangabot.prefix+"test"))
        {
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage("Hi There!").queue();
        }
        if(args[0].equalsIgnoreCase(Mangabot.prefix+"pic"))
        {
            event.getChannel().sendTyping().queue();
            EmbedBuilder pic = new EmbedBuilder();
            pic.setTitle("😇Here!");
            pic.setDescription("Nothing...");
            pic.setColor(0x12c918);
            pic.setFooter("You...", event.getMember().getUser().getAvatarUrl());

            event.getChannel().sendMessageEmbeds(pic.build()).queue();
            pic.clear();
        }

    }
}
