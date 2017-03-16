package com.cc.activity.controller;

import com.cc.activity.bean.Activity;
import com.cc.activity.constants.ActivityTypeEnum;
import com.cc.activity.service.ActivityService;
import com.cc.activity.service.StorageService;
import com.cc.activity.constants.ActivityConstants;
import com.cc.activity.util.ActivityUtil;
import com.cc.common.model.ResponseDTO;
import com.cc.common.model.ResponseDTOFactory;
import com.cc.common.util.ImageUtil;
import com.cc.exception.ActivityException;
import com.cc.exception.BizException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * Created by xn032607 on 2017/2/24.
 */
@Controller
@RequestMapping("/manage")
public class ActivityManageController {

    private Logger logger = Logger.getLogger(getClass());
    // 图片保存地址
    @Value("${image_savePath}")
    public String savePath;
    // 图片获取地址
    @Value("${image_relativePath}")
    public String relativePath;
    // 图片限制大小
    @Value("${image_size}")
    public Integer imageSize;
    @Value("${activity_default_badminton_image_path}")
    public String defaultBadmintonImagePath;
    @Value("${activity_default_card_image_path}")
    public String defaultCardImagePath;
    @Value("${activity_default_swim_image_path}")
    public String defaultSwimImagePath;

    @Autowired
    private StorageService fileSystemStorageService;
    @Autowired
    private ActivityService activityService;

    @RequestMapping("/admin")
    public String index() {
        return "manage/admin";
    }

    @RequestMapping(value = "/activity/add",method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('admin')")
    @ResponseBody
    public Object addActivities(@RequestParam(value = "file", required = false) MultipartFile file,Activity activity,Date aDate) {
        ResponseDTOFactory<ResponseDTO> responseDTOFactory = ResponseDTO::new;
        try {
            LocalDate activityDate = aDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if(activity != null) {
                activity.setActivityDate(activityDate);
            }
            ActivityUtil.validateParams(activity);
            String activityImagePath = null;
            switch (ActivityTypeEnum.getActivityTypeEnum(activity.getActivityType())) {
                case cards:
                    activityImagePath = defaultCardImagePath;
                    break;
                case swim:
                    activityImagePath = defaultSwimImagePath;
                    break;
                default:
                    activityImagePath = defaultBadmintonImagePath;
                    break;
            }
            if(!file.isEmpty()) {
                ImageUtil.checkFile(file,imageSize);
                String fileName = fileSystemStorageService.store(file);
                Path path = fileSystemStorageService.load(fileName);
                activityImagePath = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                        .build().toString();
            }
            activity.setImagePath(activityImagePath);
            activity.setStatus(ActivityConstants.ACTIVE_STATUS);
            activityService.addActivity(activity);

            return responseDTOFactory.create(true,null);
        } catch (ActivityException | IllegalArgumentException | BizException e) {
            return responseDTOFactory.create(false,e.getMessage());
        } catch (Exception e) {
            logger.error(e);
            return responseDTOFactory.create(false,"系统内部错误");
        }
    }

    @RequestMapping("/activity/findAll")
    @ResponseBody
    public Object findAllActivities() {
        return activityService.getAllActivities();
    }

    @RequestMapping("/activity/types")
    @ResponseBody
    public Object findAllActivityTypes() {
        return activityService.findAllActivityTypes();
    }

    @RequestMapping(value = "/activity/delete",method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('admin')")
    @ResponseBody
    public Object delete(@RequestParam("ids") List<Integer> ids) {
        ResponseDTOFactory<ResponseDTO> responseDTOFactory = ResponseDTO::new;
        if(CollectionUtils.isEmpty(ids)) {
            return responseDTOFactory.create(false,"请选择要删除的行");
        }
        try {
            activityService.deleteActivityByIds(ids);
            return responseDTOFactory.create(true,null);
        } catch (Exception e) {
            logger.error(e);
            return responseDTOFactory.create(false,"系统内部错误");
        }
    }

    @RequestMapping(value = "/activity/update",method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('admin')")
    @ResponseBody
    public Object update(@RequestParam(value = "file", required = false) MultipartFile file,Activity activity,Date aDate) {
        ResponseDTOFactory<ResponseDTO> responseDTOFactory = ResponseDTO::new;
        try {
            LocalDate activityDate = aDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if(activity != null) {
                activity.setActivityDate(activityDate);
            }
            ActivityUtil.validateParams(activity);
            if(!file.isEmpty()) {
                ImageUtil.checkFile(file,imageSize);
                String fileName = fileSystemStorageService.store(file);
                Path path = fileSystemStorageService.load(fileName);
                String activityImagePath = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                        .build().toString();
                activity.setImagePath(activityImagePath);
            }
            activityService.updateActivity(activity);

            return responseDTOFactory.create(true,null);
        } catch (ActivityException | IllegalArgumentException | BizException e) {
            return responseDTOFactory.create(false,e.getMessage());
        } catch (Exception e) {
            logger.error(e);
            return responseDTOFactory.create(false,"系统内部错误");
        }
    }
}
