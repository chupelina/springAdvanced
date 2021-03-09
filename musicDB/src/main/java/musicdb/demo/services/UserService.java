package musicdb.demo.services;

import musicdb.demo.models.serviceModels.UserRegistrationServiceModel;

public interface UserService {
    void seedUsers();

    void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel);

    boolean isExistingWithSameName(String username);
}
