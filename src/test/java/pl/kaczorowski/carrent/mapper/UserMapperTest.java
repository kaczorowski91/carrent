package pl.kaczorowski.carrent.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.kaczorowski.carrent.dto.UserDto;
import pl.kaczorowski.carrent.entity.User;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void mapToUserTest() {
        //Given
        UserDto userDto = new UserDto("TestFirstname", "TestLastName", "000");
        //Then
        User user = userMapper.mapToUser(userDto);
        //When
        Assert.assertEquals(userDto.getFirstName(), user.getFirstName());
        Assert.assertEquals(userDto.getLastName(), user.getLastName());
        Assert.assertEquals(userDto.getPesel(), user.getPesel());

    }

    @Test
    public void mapToUserDtoTest() {
        //Given
        User user = new User("TestFirstname", "TestLastName", "000");
        //Then
        UserDto userDto = userMapper.mapToUserDto(user);
        //When
        Assert.assertEquals(user.getFirstName(), userDto.getFirstName());
        Assert.assertEquals(user.getLastName(), userDto.getLastName());
        Assert.assertEquals(user.getPesel(), userDto.getPesel());

    }

    @Test
    public void mapToUserDtoListTest() {
        //Given
        User user1 = new User("name1", "lastName1", "001");
        User user2 = new User("name2", "lastName2", "002");
        User user3 = new User("name3", "lastName3", "003");
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        //Then
        List<UserDto> userDtoList = userMapper.mapToUserDtoList(userList);
        int count = userList.size();
        //When
        Assert.assertEquals(userDtoList.size(), count);
        Assert.assertEquals(userDtoList.get(0).getFirstName(), userList.get(0).getFirstName());
        Assert.assertEquals(userDtoList.get(1).getLastName(), userList.get(1).getLastName());
        Assert.assertEquals(userDtoList.get(2).getPesel(), userList.get(2).getPesel());

    }
}