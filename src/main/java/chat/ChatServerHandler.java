package chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    //GlobalEventExecutor.INSTANCE是全局的事件执行器，是一个单例
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[ Client ]" + channel.remoteAddress() + " online " + sdf.format(new Date()) + "\n");
        channelGroup.add(channel);
        System.out.println(ctx.channel().remoteAddress() + " online"+ "\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[ Client ]" + channel.remoteAddress() + " offline " + sdf.format(new Date()));
        System.out.println(channel.remoteAddress() + " offline"+ "\n");
        System.out.println("channelGroup size=" + channelGroup.size());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (channel != ch) {
                ch.writeAndFlush("[ Client ]" + channel.remoteAddress() + " send msg：" + msg + "\n");
            } else {
                ch.writeAndFlush("[ Client self ] send msg：" + msg + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //关闭通道
        ctx.close();
    }
}
