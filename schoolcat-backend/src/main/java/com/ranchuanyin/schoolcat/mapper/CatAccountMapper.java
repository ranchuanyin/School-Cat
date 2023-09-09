package com.ranchuanyin.schoolcat.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ranchuanyin.schoolcat.domain.CatAccount;
import com.ranchuanyin.schoolcat.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author 冉船银
 * @description 针对表【cat_account】的数据库操作Mapper
 * @createDate 2023-04-26 10:26:54
 * @Entity generator/user.domain.CatAccount
 */
@Mapper
public interface CatAccountMapper extends BaseMapper<CatAccount> {
    @Select("select * from cat_account where username = #{text} or email = #{text}")
    User findAccountUserByNameOrEmail(String text);

    @Update("update cat_account set password = #{password} where email = #{email}")
    int resetPasswordByEmail(String password, String email);


    @Update("update cat_account set experience = experience+#{experience}  where id = #{Id}")
    int IncreaseExperience(Long Id, int experience);
}




