package warui_slime.mangabot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.GatewayIntent;
import warui_slime.mangabot.Commands.Clear;
import warui_slime.mangabot.Commands.Jjk;
import warui_slime.mangabot.Commands.Pingme;
import warui_slime.mangabot.Commands.Test;
import warui_slime.mangabot.Commands.Testimg;
import warui_slime.mangabot.Commands.Tokyorevengers;
import warui_slime.mangabot.Events.Memes;
import warui_slime.mangabot.Events.Reactionmanage;

public class Mangabot
{
    public static JDA jda;
    public static String prefix = "$";
    public static User msguser;
    public static String curr_manga;
    public static void main(String[] args)
    {
        jda = JDABuilder.createDefault(System.getenv("manga"),
        GatewayIntent.GUILD_MEMBERS,
        GatewayIntent.GUILD_MESSAGES,
        GatewayIntent.GUILD_VOICE_STATES,
        GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
        GatewayIntent.MESSAGE_CONTENT,
        GatewayIntent.GUILD_MESSAGE_TYPING,
        GatewayIntent.GUILD_MESSAGE_REACTIONS,
        GatewayIntent.GUILD_WEBHOOKS
        ).setActivity(Activity.playing("Apex Legends")).build();
        jda.getPresence().setStatus(OnlineStatus.IDLE);
    
        System.out.println("ok");
        jda.addEventListener(new Test());
        jda.addEventListener(new Clear());
        jda.addEventListener(new Pingme());
        jda.addEventListener(new Testimg());
        jda.addEventListener(new Tokyorevengers());
        jda.addEventListener(new Reactionmanage());
        jda.addEventListener(new Jjk());
        jda.addEventListener(new Memes());
        


    }
}
