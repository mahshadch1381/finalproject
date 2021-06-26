package Server;

public class MainServer {
    public void pop(){
        new carryoverperson_get1_server().start();
        new carryoverperson_give1_server().start();
        new clientlist_server().start();
        new count_of_followers_server().start();
        new count_of_following_server().start();
        new count_of_likes_server().start();
        new counts_of_reposts().start();
        new deletingacount_server().start();
        new edit_server().start();
        new findprofile_server().start();
        new followingfiles2_server2().start();
        new followingfiles_server().start();
        new followingserver().start();
        new getpersoninfo_server().start();
        new like_server().start();
        new loginServer().start();
        new mute_server().start();
        new personposts_server().start();
        new postdetail_server().start();
        new postdetails2_server2().start();
        new postingServer().start();
        new ReadingComments_Server().start();
        new recoverypass_server().start();
        new repost_server().start();
        new SavingComments_server().start();
        new showingposts_server().start();
        new signupServer().start();
        new unfollowingserver().start();
        new unmute_server().start();
    }
    public static void main(String[] args){
        new MainServer().pop();
    }

}
