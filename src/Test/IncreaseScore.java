package Test;

import DAL.Repository.UserRepository;

public class IncreaseScore {

    public static void main(String[] args) {
        UserRepository userRepository= new UserRepository();

        userRepository.increaseScore("lvtdel2");
    }
}
