package usst.edu.cn.sharebooks.network;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import usst.edu.cn.sharebooks.model.donate.AllAvailableBook;
import usst.edu.cn.sharebooks.model.donate.DeleteDonateBookResponse;
import usst.edu.cn.sharebooks.model.donate.UserAddDonateBookResponse;
import usst.edu.cn.sharebooks.model.donate.UserPersonalDonateStallResponse;
import usst.edu.cn.sharebooks.model.douban.DouBanResponse;
import usst.edu.cn.sharebooks.model.order.OrderBookActionResponse;
import usst.edu.cn.sharebooks.model.order.OrderDealResultResponse;
import usst.edu.cn.sharebooks.model.order.PersonalOrderResponse;
import usst.edu.cn.sharebooks.model.search.SearchJiaoCaiResponse;
import usst.edu.cn.sharebooks.model.sellstall.AddSellBookResponse;
import usst.edu.cn.sharebooks.model.sellstall.AllUserSellStallResponse;
import usst.edu.cn.sharebooks.model.sellstall.DeleteSellResponse;
import usst.edu.cn.sharebooks.model.sellstall.OneUserSellStallResponse;
import usst.edu.cn.sharebooks.model.sellstall.SellBookStallList;
import usst.edu.cn.sharebooks.model.user.LoginResponse;
import usst.edu.cn.sharebooks.model.user.RegisterResponser;
import usst.edu.cn.sharebooks.model.user.UpdateUserInfoResponse;

public interface ApiInterface {

   //    String HOST = "http://192.168.1.103:8080/ShareBookProject_1.0/";
    String HOST = "http://101.132.129.234/ShareBookProject_1.0/";
    //这里这个Host不能写localhost,而必须写电脑的Ipv4的地址
    //这样  我们终于访问上server了
 //  String BookImageBaseURL = "http://192.168.1.105:8080/ShareBookProject_1.0/images/";
 //    String BookImageBaseURL = "http://101.132.129.234/ShareBookProject_1.0/images/";

    //  所有教材图片的访问基本地址
   //     String AllBookImageUrl = "http://192.168.1.103:8080/ShareBookProject_1.0/allimages/";

      String AllBookImageUrl = "http://101.132.129.234/ShareBookProject_1.0/allimages/";
    //用户头像的基本地址
   //     String UserImageUrl = "http://192.168.1.103:8080/userImages/";
   String UserImageUrl = "http://101.132.129.234/userImages/";


    @GET("GivenBookServlet")
    Observable<AllAvailableBook> mGivenBookApi();

    @POST("GivenBookServlet")
    @FormUrlEncoded
    Observable<UserAddDonateBookResponse> addGivenBook(@Query("RequestWay")int requestWay,@FieldMap Map<String,String> infs);

    @POST("GivenBookServlet")
    Observable<DeleteDonateBookResponse> deleteDonateBook(@Query("RequestWay")int requestWay,@Query("BookName")String bookName,@Query("UserId")int userId);

    @POST("GivenBookServlet")
    Observable<UserPersonalDonateStallResponse> lookDonateBook(@Query("RequestWay")int requestWay,@Query("DonateUserId")int userId);

    @POST("BuySellBookServlet")
    Observable<OneUserSellStallResponse> loadOneUserAllSellBook(@Query("RequestWay")int requestWay,@Query("UserId")int userId);

    @POST("OrderLogicDealServlet")
    @FormUrlEncoded
    Observable<OrderBookActionResponse> orderBook(@Query("RequestWay") int requestWay,@FieldMap Map<String,String> infs);

    @POST("OrderLogicDealServlet")
    Observable<PersonalOrderResponse> orderList(@Query("RequestWay")int requestWay,@Query("UserId")int userId);

    @POST("OrderLogicDealServlet")
    @FormUrlEncoded
    Observable<OrderDealResultResponse> donateOrderResultDeal(@Query("RequestWay")int requestWay,@Query("Result")int result,@FieldMap Map<String,String> infs);

    @GET("PersonalBookStall")
    Observable<SellBookStallList> getUserSellBookStallData(@Query("StallId") int stallId);

    @FormUrlEncoded
    @POST("PersonalBookStall")
    Observable<DeleteSellResponse> postUserReqeustData(@FieldMap Map<String,String> infs);

    @FormUrlEncoded
    @POST("PersonalBookStall")
    Observable<SearchJiaoCaiResponse> postSearchJiaocaiReuqest(@FieldMap Map<String,String> infs);

    @FormUrlEncoded
    @POST("PersonalBookStall")
    Observable<AddSellBookResponse> postAddSellBookAction(@FieldMap Map<String,String> infs);

    @FormUrlEncoded
    @POST("UserRegisterServlet")
    Observable<RegisterResponser> register(@FieldMap Map<String,String> infs);

    @FormUrlEncoded
    @POST("UserLoginServlet")
    Observable<LoginResponse> login(@Field("UserName") String userName, @Field("PassWord") String passWord);

    @GET("BuySellBookServlet")
    Observable<AllUserSellStallResponse> getAllSellBookStallAction();

    @GET("https://api.douban.com/v2/book/search")
    Observable<DouBanResponse> getBookInfoFromDouban(@Query("q") String key,@Query("count") int count);

    @Multipart
    @POST("UserUpdateInformation")
    Observable<UpdateUserInfoResponse> updateUserImage(@Query("RequestWay") int requestWay, @Query("UserId") int userId,@Query("ImageUrl") String imageUrl ,@Part("Touxiang")RequestBody requestBody);

    @POST("UserUpdateInformation")
    Observable<UpdateUserInfoResponse> updateUserOtherInfo(@Query("RequestWay") int requestWay,@Query("UserId") int userId,@Query("NewString") String updateStr);
}
