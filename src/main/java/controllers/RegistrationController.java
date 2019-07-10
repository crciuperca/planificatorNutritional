package controllers;

import Constants.MeasurementUnitConstants;
import Constants.NutritionConstants;
import Utils.NutritionLogicUtil;
import entities.*;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojos.*;
import services.*;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Initially the Controller in charge of Registration functions - became universal Controller
 * TODO - REFACTOR */

@RestController
public class RegistrationController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private UserPlanService userPlanService;

    @Autowired
    private AllergyService allergyService;

    @Autowired
    private DietService dietService;

    @Autowired
    private UserAllergyService userAllergyService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserProgressService userProgressService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserMenuService userMenuService;

    private UserAccount userAccountReg;
    private UserData userDataReg;
    private UserPlan userPlanReg;
    private static final String adminEncryptedGet = "L2dldEFkbWluVXNlckRhdGEv";       // btoa("/getAdminUserData/")
    private static final String adminEncryptedSet = "L3NldEFkbWluVXNlckRhdGEv";       // btoa("/setAdminUserData/")

    @RequestMapping("/users")
    public List<UserAccount> getUsers() {
        return userAccountService.getAllUserAccounts();
    }

    @RequestMapping("/weightUnits")
    public List<String> getWeightUnits() {
        return MeasurementUnitConstants.weightUnits;
    }

    @RequestMapping("/heightUnits")
    public List<String> getHeightUnits() {
        return MeasurementUnitConstants.heightUnits;
    }

    @RequestMapping("/allergyList")
    public List<Allergy> getAllergyList() {
        return allergyService.getAllAlergies();
    }

    @RequestMapping("/users/{id}")
    public UserAccount getUser(@PathVariable long id) {
        return userAccountService.getUser(id);
    }

    @RequestMapping(method = RequestMethod.POST, value="/addUser")
    public String addUser(@RequestBody UserRegistration userRegistrationData) {
        return "Done";
    }

    @RequestMapping(method = RequestMethod.POST, value="/users/{id}")
    public void addUser(@RequestBody UserAccount userAccount, @PathVariable String id) {
        userAccountService.addUser(userAccount, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/users/{id}")
    public void removeUser(@PathVariable String id) {
        userAccountService.removeUser(id);
    }

    @RequestMapping("/create")
    public String createUser() {
        return userAccountService.createUser();
    }

    @RequestMapping("/checkUser/{username}")
    public String checkUser(@PathVariable String username) {
        if (username.length() < 6 || username.length() > 12) {
            return "Username must have between 6 and 12 characters";
        } else if (userAccountService.userExists(username)) {
            return "Username is already used";
        }
        return "Ok!";
    }

    @RequestMapping("/checkPass/{password}")
    public String checkPass(@PathVariable String password) {
        password = new String(Base64.getDecoder().decode(password));
        if (password.length() < 6 || password.length() > 12) {
            return "Password must have between 6 and 12 characters";
        } else if (!password.matches(".*\\d+.*") || !password.matches(".*[a-z]+.*") || !password.matches(".*[A-Z]+.*")) {
            return "Password must contain at least 1 uppercase letter, 1 lowercase and 1 number";
        }
        return "Ok!";
    }

    @RequestMapping("/checkEmail/{email}/{id}")
    public String checkEmail(@PathVariable String email, @PathVariable String id) {
        long userId = Long.parseLong(new String(Base64.getDecoder().decode(id)));
        int[][] a = {{1,2,},{3,4}};
        email = new String(Base64.getDecoder().decode(email));
        List<UserData> userDataList = userDataService.getAllUserData();
        for (UserData userData : userDataList) {
            if (userData.getEmail().equals(email) && userData.getUserId() != userId) {
                return "Email already used";
            }
        }
        String emailRegex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches() ? "Ok!" : "Email invalid";
    }

    @RequestMapping("/register/step1/")
    public void registrationStep1(@RequestBody RegistrationDataStep1 regDataStep1) {

        String username = new String(Base64.getDecoder().decode(regDataStep1.getUsername()));
        String password = new String(Base64.getDecoder().decode(regDataStep1.getPassword()));
        String email = new String(Base64.getDecoder().decode(regDataStep1.getEmail()));

        userAccountReg = new UserAccount(username, password);
       // userAccountService.addUser(userAccountReg);
        userDataReg = new UserData();
        userDataReg.setEmail(email);

    }

    @RequestMapping("/register/step2/")
    public String registrationStep2 (@RequestBody RegistrationDataStep2 regDataStep2) {

        String firstName = new String(Base64.getDecoder().decode(regDataStep2.getFirstName()));
        String lastName = new String(Base64.getDecoder().decode(regDataStep2.getLastName()));
        char sex = new String(Base64.getDecoder().decode(regDataStep2.getSex())).charAt(0);
        String height = new String(Base64.getDecoder().decode(regDataStep2.getHeight()));
        String hUnit = new String(Base64.getDecoder().decode(regDataStep2.gethUnit()));
        String weight = new String(Base64.getDecoder().decode(regDataStep2.getWeight()));
        String wUnit = new String(Base64.getDecoder().decode(regDataStep2.getwUnit()));
        String birthday = new String(Base64.getDecoder().decode(regDataStep2.getBirthday()));
        String tWeight = new String(Base64.getDecoder().decode(regDataStep2.gettWeight()));
        String tDate = new String(Base64.getDecoder().decode(regDataStep2.gettDate()));
        String diet = new String(Base64.getDecoder().decode(regDataStep2.getDiet()));
        String activityLevel = new String(Base64.getDecoder().decode(regDataStep2.getActivityLevel()));
        String mealsPerDay = new String(Base64.getDecoder().decode(regDataStep2.getMealsPerDay()));

        userDataReg.setFirstName(firstName);
        userDataReg.setLastName(lastName);
        userDataReg.setHeightMeasureUnit(hUnit);
        userDataReg.setWeightMeasureUnit(wUnit);
        userDataReg.setSex(sex);
        DateFormat format = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);
        try {
            userDataReg.setHeight(Double.parseDouble(height));
            userDataReg.setWeight(Double.parseDouble(weight));
            userDataReg.setBirthdate(format.parse(birthday));
            userPlanReg = new UserPlan(Double.parseDouble(tWeight), format.parse(tDate), Long.parseLong(mealsPerDay), activityLevel);
            userPlanReg.setDietId(dietService.getDietId(diet));
        } catch (Exception e) {
            return "Error";
        }


        return "Ok!";
    }

    @RequestMapping("/register/step3/")
    public String registrationStep3 (@RequestBody RegistrationDataStep3 regDataStep3) {
        userAccountService.addUser(userAccountReg);

        // add role

        long userId = userAccountService.getUserId(userAccountReg.getUsername());
        userPlanReg.setUserId(userId);
        userPlanService.addPlan(userPlanReg);
        long planId = userPlanService.getPlanId(userId);
        userDataReg.setUserId(userId);
        userDataReg.setWeightLossPlanId(planId);
        userDataReg.setPhotoID(userId);
        userDataService.addData(userDataReg);
        userRoleService.addNewUser(userId);
        img.setUserId(userId);
        uploadImage(img);
        for (int i = 0; i < regDataStep3.getAllergies().length; i++) {
            if (regDataStep3.getAllergies()[i]) {
                userAllergyService.addAllergy(userId, i + 1);
            }
        }
        return "Ok!";
    }

    @PostMapping(value = "/register")
    public String register(@RequestBody UserRegistration userRegistration) {
//        if(!userRegistration.getPassword().equals((userRegistration.getPasswordConfirmation()))) {
//            return "Passwords do not match";
//        } else if (userAccountService.getUser(userRegistration.getUsername()) != null) {
//            return "User already exists";
//        }

        userAccountService.addUser(new UserAccount(userRegistration.getUsername(), userRegistration.getPassword()));
        return "Account created. Please login.";
    }

    @RequestMapping("/signIn/")
    public String signIn(@RequestBody RegistrationDataStep1 userAccount) {
        String username = new String(Base64.getDecoder().decode(userAccount.getUsername()));
        String password = new String(Base64.getDecoder().decode(userAccount.getPassword()));
        JSONObject jsonObject = new JSONObject();
        Long userId = userAccountService.getUserId(username, password);
        UserAccount gotUserAccount = (userId != null) ? userAccountService.getUser(userId) : null;
        String errMsg = (userId != null && (gotUserAccount != null && gotUserAccount.getEnabled() == 'Y')) ? "Ok!" : "User not found";
        String firstName = (userId != null) ? userDataService.getUserDataByUserId(userId.longValue()).getFirstName() : "";

        jsonObject = new JSONObject();
        jsonObject.appendField("errMsg", new String(Base64.getEncoder().encode(errMsg.getBytes())));
        jsonObject.appendField("firstName", new String(Base64.getEncoder().encode(firstName.getBytes())));
        jsonObject.appendField("userId",
                (userId != null && (gotUserAccount != null && gotUserAccount.getEnabled() == 'Y')) ? userId.longValue() : -1);
        String userRoleName = userRoleService.getUserRoleName(userId);
        jsonObject.appendField("isAdmin", userRoleName == null || !userRoleName.equals("admin") ? 0 : 1);
        return jsonObject.toJSONString();
    }

    @RequestMapping("/getAccountInfo/{userId}")
    public String getAccountInfo(@PathVariable long userId) {
        UserData userData = userDataService.getUserDataByUserId(userId);
        UserAccount userAccount = userAccountService.getUser(userId);
        JSONObject jsonObject = new JSONObject();

        if (userData == null) {
            jsonObject.appendField("errMsg", new String(Base64.getEncoder().encode("User not found".getBytes())));
            return jsonObject.toJSONString();
        }

        jsonObject.appendField("errMsg", new String(Base64.getEncoder().encode("Ok!".getBytes())));
        jsonObject.appendField("password", new String(Base64.getEncoder().encode(userAccount.getPassword().getBytes())));
        jsonObject.appendField("email", new String(Base64.getEncoder().encode(userData.getEmail().getBytes())));
        return jsonObject.toJSONString();
    }

    @RequestMapping("/getUserInfo/{userId}")
    public String getUserInfo(@PathVariable long userId) {
        UserData userData = userDataService.getUserDataByUserId(userId);
        JSONObject jsonObject = new JSONObject();

        if (userData == null) {
            jsonObject.appendField("errMsg", new String(Base64.getEncoder().encode("User not found".getBytes())));
            return jsonObject.toJSONString();
        }
        UserPlan userPlan = userPlanService.getPlanByUserId(userId);
        Diet userDiet = dietService.getDietById(userPlan.getDietId());
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        jsonObject.appendField("errMsg", new String(Base64.getEncoder().encode("Ok!".getBytes())));
        jsonObject.appendField("firstName", new String(Base64.getEncoder().encode(userData.getFirstName().getBytes())));
        jsonObject.appendField("lastName", new String(Base64.getEncoder().encode(userData.getLastName().getBytes())));
        jsonObject.appendField("sex", new String(Base64.getEncoder().encode((userData.getSex() == 'M' ? "Male" : "Female").getBytes())));
        jsonObject.appendField("height", new String(Base64.getEncoder().encode(userData.getHeight().toString().getBytes())));
        jsonObject.appendField("hUnit", new String(Base64.getEncoder().encode(userData.getHeightMeasureUnit().getBytes())));
        jsonObject.appendField("weight", new String(Base64.getEncoder().encode(userData.getWeight().toString().getBytes())));
        jsonObject.appendField("wUnit", new String(Base64.getEncoder().encode(userData.getWeightMeasureUnit().toString().getBytes())));
        jsonObject.appendField("birthday", new String(Base64.getEncoder().encode(dateFormat.format(userData.getBirthdate()).getBytes())));
        jsonObject.appendField("tWeight", new String(Base64.getEncoder().encode(userPlan.getTargetWeight().toString().getBytes())));
        jsonObject.appendField("tDate", new String(Base64.getEncoder().encode(dateFormat.format(userPlan.getTargetDate()).getBytes())));
        jsonObject.appendField("diet", new String(Base64.getEncoder().encode(userDiet.getDietName().getBytes())));
        jsonObject.appendField("activityLevel", new String(Base64.getEncoder().encode(userPlan.getActivityLevel().getBytes())));
        jsonObject.appendField("mealsPerDay", new String(Base64.getEncoder().encode((""+userPlan.getMealsPerDay()).getBytes())));

        return jsonObject.toJSONString();
    }

    @RequestMapping("/updateAccountInfo/")
    public String updateAccountInfo(@RequestBody RegistrationDataStep1 regDataStep1) {

        String password = new String(Base64.getDecoder().decode(regDataStep1.getPassword()));
        String email = new String(Base64.getDecoder().decode(regDataStep1.getEmail()));
        long id = Long.parseLong(new String(Base64.getDecoder().decode(regDataStep1.getId())));
        if (userDataService.getIdByEmail(email) != null && userDataService.getIdByEmail(email) != id) {
            return "Email already used";
        } else  if (password.length() < 6 || password.length() > 12) {
            return "Password must have between 6 and 12 characters";
        } else if (!password.matches(".*\\d+.*") || !password.matches(".*[a-z]+.*") || !password.matches(".*[A-Z]+.*")) {
            return "Password must contain at least 1 uppercase letter, 1 lowercase and 1 number";
        }
        userAccountService.updateAccountPassword(id, password);
        userDataService.updateDataEmail(id, email);

        return "Ok!";
    }

    @RequestMapping("/updateUserInfo/")
    public String updateAccountInfo (@RequestBody RegistrationDataStep2 regDataStep2) {
        long id = Long.parseLong(new String(Base64.getDecoder().decode(regDataStep2.getId())));
        String firstName = new String(Base64.getDecoder().decode(regDataStep2.getFirstName()));
        String lastName = new String(Base64.getDecoder().decode(regDataStep2.getLastName()));
        char sex = new String(Base64.getDecoder().decode(regDataStep2.getSex())).charAt(0);
        String height = new String(Base64.getDecoder().decode(regDataStep2.getHeight()));
        String hUnit = new String(Base64.getDecoder().decode(regDataStep2.gethUnit()));
        String weight = new String(Base64.getDecoder().decode(regDataStep2.getWeight()));
        String wUnit = new String(Base64.getDecoder().decode(regDataStep2.getwUnit()));
        String birthday = new String(Base64.getDecoder().decode(regDataStep2.getBirthday()));
        String tWeight = new String(Base64.getDecoder().decode(regDataStep2.gettWeight()));
        String tDate = new String(Base64.getDecoder().decode(regDataStep2.gettDate()));
        String diet = new String(Base64.getDecoder().decode(regDataStep2.getDiet()));
        String activityLevel = new String(Base64.getDecoder().decode(regDataStep2.getActivityLevel()));
        String mealsPerDay = new String(Base64.getDecoder().decode(regDataStep2.getMealsPerDay()));

        DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        try {
            long dietId = dietService.getDietId(diet);
            userDataService.updateUserDataInfo(id, firstName, lastName, sex, hUnit, wUnit,
                    format.parse(birthday), Double.parseDouble(height), Double.parseDouble(weight));
            userPlanService.updateUserPlanInfo(id, dietId, Double.parseDouble(tWeight), format.parse(tDate), Long.parseLong(mealsPerDay), activityLevel);

        } catch (Exception e) {
            return "Error";
        }


        return "Ok!";
    }

//    @RequestMapping("/roles/{userId}")
//    public List<UserRole> listRoles(@PathVariable long userId) {
//        return userAccountService.getUserRoles(userId);
//    }

    @RequestMapping("/signout")
    public String signOut() {
        return "Goodbye!";
    }

    @RequestMapping("/getDiets/")
    public String getDiets() {
        JSONObject jsonObject = new JSONObject();
        List<String> dietList = dietService.getDietList();

        jsonObject.appendField("weightLossPlanList", dietList);//new String(Base64.getEncoder().encode(dietListString.getBytes())));

        return jsonObject.toJSONString();
    }
    ProfileImage img;
    @RequestMapping("/uploadImage/")
    public void uploadImage(@RequestBody ProfileImage profileImage) {
        String image = profileImage.getImageSrc();
        long userId = profileImage.getUserId();
        if (userId == -1) {
            userId = userAccountReg.getId();
            img = profileImage;
            return;
        }
        profileImage = img;
        if(image.length() > 0) {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(userId + "profilePhotoSrc.txt", "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            writer.print(image);
            writer.close();
        } else {
            Path filePath = Paths.get(userId + "profilePhotoSrc.txt");
            try {
                Files.delete(filePath);
                PrintWriter writer = new PrintWriter(userId + "profilePhotoSrc.txt", "UTF-8");
                writer.print("");
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/downloadImage/{id}")
    public String downloadImage(@PathVariable String id) {
        long userId = Long.parseLong(new String(Base64.getDecoder().decode(id)));
        String imageSrc = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(userId + "profilePhotoSrc.txt"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            imageSrc = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                //br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return imageSrc;
    }

    @RequestMapping("/getProgress/{id}")
    public String getUserProgress(@PathVariable String id) {
        long userId = Long.parseLong(new String(Base64.getDecoder().decode(id)));
        JSONObject jsonObject = new JSONObject();

        if (userId < 0 || userAccountService.getUser(userId) == null) {
            jsonObject.appendField("errMsg", "User not found");
            return jsonObject.toJSONString();
        }

        List<UserProgress> userProgressList = userProgressService.getUserProgress(userId);
        if (userProgressList.size() == 0) {
            jsonObject.appendField("errMsg", "No progress found");
            return jsonObject.toJSONString();
        } else {
            Calendar calendar = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            List<long[]> weeklyCaloriesList = new ArrayList<>();
            List<String[]> weeklyDateList = new ArrayList<>();
            calendar.setTime(userProgressList.get(0).getDay());
            int firstDayIdx = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            calendar.setTime(userProgressList.get(userProgressList.size() - 1).getDay());
            int lastDayIdx = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            int newSize = userProgressList.size() + (firstDayIdx - 1) + (7 - lastDayIdx);

            long[] weeklyCalories = {0, 0, 0, 0, 0, 0, 0};
            String[] weeklyDate = {"", "", "", "", "", "", ""};
            int listCounter = 0;
            for (int i = 0; i < newSize; i++) {
                if ((i % 7 == 0 && i != 0) || i == newSize - 1) {
                    weeklyCaloriesList.add(weeklyCalories);
                    weeklyDateList.add(weeklyDate);
                    weeklyCalories = new long[]{0, 0, 0, 0, 0, 0, 0};
                    weeklyDate = new String[]{"", "", "", "", "", "", ""};
                }
                if (!(i < firstDayIdx - 1 || i >= userProgressList.size() + firstDayIdx - 1)) {
                    weeklyCalories[i % 7] = userProgressList.get(listCounter).getCalories();
                    weeklyDate[i % 7] = dateFormat.format(userProgressList.get(listCounter).getDay());
                    listCounter++;
                }
            }

            jsonObject.appendField("errMsg", "Ok!");
            jsonObject.appendField("weeklyCaloriesList", weeklyCaloriesList);
            jsonObject.appendField("weeklyDateList", weeklyDateList);

            return jsonObject.toJSONString();
        }
    }

    @RequestMapping(adminEncryptedGet)
    public String getAdminUserData() {
        JSONObject jsonObject = new JSONObject();
        List<UserAccount> userAccountList = userAccountService.getAllUserAccounts();
        List<AdminUserData> adminUserDataList = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        for (UserAccount userAccount : userAccountList) {
            String userRole = userRoleService.getUserRoleName(userAccount.getId());
            UserData userData = userDataService.getUserDataByUserId(userAccount.getId());
            adminUserDataList.add(new AdminUserData(
                userAccount.getUsername(),
                userData != null ? userData.getFirstName() + " " + userData.getLastName() : "",
                userData != null ? userData.getEmail() : "",
                userRole,
                userAccount.getEnabled() == 'Y' ? true : false,
                dateFormat.format(userAccount.getCreationDate())
            ));
        }
        jsonObject.appendField("adminUserData", adminUserDataList);
        return jsonObject.toJSONString();
    }

    @RequestMapping(adminEncryptedSet)
    public void setAdminData(@RequestBody List<AdminUserData> adminUserDataList) {
        for (AdminUserData userData : adminUserDataList) {
            long userId = userAccountService.getUserId(userData.getUsername());
            if (userData.getEnabled()) {
                userAccountService.enableUserAccount(userId);
            } else {
                userAccountService.disableUserAccount(userId);
            }
        }
    }

    @RequestMapping("/getDietData/{id}")
    public String getUserDietData(@PathVariable String id) {
        long userId = Long.parseLong(new String(Base64.getDecoder().decode(id)));
        JSONObject jsonObject = new JSONObject();

        UserAccount userAccount = userAccountService.getUser(userId);
        UserPlan userPlan = userPlanService.getPlanByUserId(userId);
        UserData userData = userDataService.getUserDataByUserId(userId);
        Diet userDiet = dietService.getDietById(userPlan.getDietId());
        UserProgress todayUserProgress = userProgressService.getLastUserProgress(userId);
        long todayCalories = 0;
        if (todayUserProgress == null || NutritionLogicUtil.getDaysBetween(todayUserProgress.getDay(), new Date()) >= 1) {
            todayCalories = NutritionLogicUtil.getTodayCalories(
                    userData.getWeight(),
                    userPlan.getTargetWeight(),
                    userAccount.getCreationDate(),
                    userPlan.getTargetDate(),
                    userProgressService.getTotalUserCalories(userId),
                    userData.getSex(),
                    userPlan.getActivityLevel(),
                    userData.getBirthdate()
            );
            userProgressService.addUserProgress(userId, new Date(), todayCalories);
        } else {
            todayCalories = todayUserProgress.getCalories();
        }

        jsonObject.appendField("todayCalories", todayCalories);
        jsonObject.appendField("proteinRatio", userDiet.getProteinRatio());
        jsonObject.appendField("carbRatio", userDiet.getCarbRatio());
        jsonObject.appendField("fatRatio", userDiet.getLipidRatio());

        return jsonObject.toJSONString();
    }

    @RequestMapping("/getUserMenu/{id}")
    public String getUserMenu(@PathVariable String id) {
        long userId = Long.parseLong(new String(Base64.getDecoder().decode(id)));
        JSONObject jsonObject = new JSONObject();

        UserMenuData userMenuData = new UserMenuData();
        UserPlan userPlan = userPlanService.getPlanByUserId(userId);
        Diet userDiet = dietService.getDietById(userPlan.getDietId());
        List<UserMenu> userMenuList = userMenuService.getLatestUserMenu(userId);

        if(userMenuList != null && userMenuList.size() > 0 &&
                NutritionLogicUtil.getDaysBetween(userMenuList.get(0).getDay(), new Date()) < 1) {
                userMenuData = buildUserMenu(userMenuList);
        } else {
            UserProgress todayUserProgress = userProgressService.getLastUserProgress(userId);
            userProgressService.updateUserCalories(userId, todayUserProgress.getCalories(), todayUserProgress.getDay());


            long[] userAllergyIdArray = new long[]{-1};
            List<Long> userAllergyIdList = userAllergyService.getUserAllergies(userId);
            if (userAllergyIdList != null && userAllergyIdList.size() > 0) {
                userAllergyIdArray = userAllergyService.getUserAllergies(userId).stream().mapToLong(l -> l).toArray();
            }

            userMenuList = NutritionLogicUtil.createUserMenu(
                    todayUserProgress,
                    userDiet,
                    userPlan,
                    foodService.getSnacksWithoutAllergy(userAllergyIdArray),
                    foodService.getMeatsWithoutAllergy(userAllergyIdArray),
                    foodService.getVegetablestWithoutAllergy(userAllergyIdArray),
                    foodService.getFruitsWithoutAllergy(userAllergyIdArray),
                    foodService.getSidedishesWithoutAllergy(userAllergyIdArray)
            );
            userMenuData = buildUserMenu(userMenuList);
        }
        jsonObject.appendField("userMenuData", userMenuData);
        jsonObject.appendField("errMsg", "Ok!");
        return jsonObject.toJSONString();
    }

    @RequestMapping("/updateCalories/{id}/{calories}")
    public String updateCalories(@PathVariable String id, @PathVariable String calories) {
        long userId = Long.parseLong(new String(Base64.getDecoder().decode(id)));
        long todayCalories = Long.parseLong(new String(Base64.getDecoder().decode(calories)));

        UserProgress todayUserProgress = userProgressService.getLastUserProgress(userId);
        if (todayUserProgress == null || NutritionLogicUtil.getDaysBetween(todayUserProgress.getDay(), new Date()) > 1) {
            userProgressService.addUserProgress(userId, new Date(), todayCalories);
        } else  {
            userProgressService.updateUserCalories(userId, todayCalories, todayUserProgress.getDay());
        }

        return "Ok!";
    }

    public UserMenuData buildUserMenu(List<UserMenu> userMenuList) {
        List<FoodQtyPair> meal1 = new ArrayList<>();
        List<FoodQtyPair> meal2 = new ArrayList<>();
        List<FoodQtyPair> meal3 = new ArrayList<>();
        List<FoodQtyPair> meal4 = new ArrayList<>();
        List<FoodQtyPair> meal5 = new ArrayList<>();
        int mealsPerDay = 0;

        for (UserMenu userMenu : userMenuList) {
            Food thisFood = foodService.getFoodById(userMenu.getFoodId());
            mealsPerDay = Math.max(mealsPerDay, (int)userMenu.getMealno());
            FoodQtyPair thisFoodQtyPair = new FoodQtyPair(thisFood.getName(), "" + userMenu.getQuantity() + "" + thisFood.getMeasureUnit() + " ");
            switch((int)userMenu.getMealno()) {
                case 1:
                    meal1.add(thisFoodQtyPair);
                    break;
                case 2:
                    meal2.add(thisFoodQtyPair);
                    break;
                case 3:
                    meal3.add(thisFoodQtyPair);
                    break;
                case 4:
                    meal4.add(thisFoodQtyPair);
                    break;
                case 5:
                    meal5.add(thisFoodQtyPair);
                    break;

            }
        }

        return new UserMenuData(mealsPerDay, meal1, meal5, meal3, meal4, meal2);
    }
}
