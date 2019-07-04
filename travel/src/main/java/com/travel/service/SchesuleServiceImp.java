package com.travel.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.model.Category;
import com.travel.model.Project;
import com.travel.model.Schedule;
import com.travel.model.ScheduleForm;
import com.travel.repository.CategoryRepository;
import com.travel.repository.ProjectRepository;
import com.travel.repository.ScheduleRepository;

@Service
public class SchesuleServiceImp implements ScheduleService{

	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<Schedule> findAllSchadule(Integer projectId) {
		return projectRepository.findById(projectId).get().getScheduleList();
	}
	
	@Override
	public Schedule findOne(Integer scheduleId) {
		return scheduleRepository.findById(scheduleId).get();
	}

	@Override

	public void update(ScheduleForm scheduleForm, Schedule schedule, Integer projectId) {
		Category category = categoryRepository.findById(scheduleForm.getCateId()).get();
		Project project = projectRepository.findById(projectId).get();
		
		schedule.setScName(scheduleForm.getTitle());
		String Start=scheduleForm.getStartDay()+scheduleForm.getStart();
		SimpleDateFormat startFormat = new SimpleDateFormat("yyyy-MM-ddhh:mm");
		startFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date startTime = null;
		try {
			startTime = startFormat.parse(Start);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		String Last=scheduleForm.getEndDay()+scheduleForm.getEnd();
		SimpleDateFormat lastFormat = new SimpleDateFormat("yyyy-MM-ddhh:mm");
		lastFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date lastTime = null;
        try {
			lastTime = lastFormat.parse(Last);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
        schedule.setStartTime(startTime);
		schedule.setLastTime(lastTime);		
		schedule.setCategory(category);
		schedule.setDetails(scheduleForm.getText());
		schedule.setProject(project);

		scheduleRepository.saveAndFlush(schedule);
	}
	//スケジュール新規登録データ保存
	@Override
	public Schedule create(ScheduleForm addForm, Integer projectId) {
		Category category = categoryRepository.findById(addForm.getCateId()).get();
		Project project = projectRepository.findById(projectId).get();
		Schedule schedule = new Schedule();
		schedule.setScName(addForm.getTitle());
		String Start=addForm.getStartDay()+addForm.getStart();
		SimpleDateFormat startFormat = new SimpleDateFormat("yyyy-MM-ddhh:mm");
		startFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date startTime = null;
		try {
			startTime = startFormat.parse(Start);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		schedule.setStartTime(startTime);
		String Last=addForm.getEndDay()+addForm.getEnd();
		SimpleDateFormat lastFormat = new SimpleDateFormat("yyyy-MM-ddhh:mm");
		lastFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date lastTime = null;
        try {
			lastTime = lastFormat.parse(Last);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        String uploadFilePath = imageSave(addForm,projectId);
        schedule.setImg(uploadFilePath);
        
		schedule.setLastTime(lastTime);		
		schedule.setCategory(category);
		schedule.setDetails(addForm.getText());
		schedule.setProject(project);
		System.out.println("savemae");
		return scheduleRepository.saveAndFlush(schedule);
	}

	@Override
	public void delete(Integer scheduleId) {
		scheduleRepository.deleteById(scheduleId);
	}
	
	public String imageSave(ScheduleForm addForm, Integer projectId) {
		//画像処理
        String extension = "";
        System.out.println(addForm.getPicture());
        String imageName = addForm.getPicture().getOriginalFilename();
        StringBuffer filePath = new StringBuffer("./app/uploads")
                .append(File.separator); 
        
        try {
            File uploadDir = new File(Objects.requireNonNull(filePath + projectId.toString()));

            // アップロードファイルを格納するディレクトリがなければ作成する
            if(!uploadDir.exists())    uploadDir.mkdirs();
           
            assert imageName != null;
            int dot = imageName.lastIndexOf(".");
            if (dot > 0) extension = imageName.substring(dot).toLowerCase();
            String filename = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now());

	        // アップロードファイルを置く
	        File uploadFile = new File(uploadDir.getPath() + File.separator + filename + extension);
	        File accessFile = new File(File.separator + "uploads" + File.separator + filename + extension);
	
	        System.out.println(uploadFile.getPath());
	        System.out.println(accessFile.getPath());
	        
	        byte[] bytes = addForm.getPicture().getBytes();
	        BufferedOutputStream uploadFileStream =
	                new BufferedOutputStream(new FileOutputStream(uploadFile));
	        uploadFileStream.write(bytes);
	        uploadFileStream.close();
	
	        return accessFile.getPath();
	    } catch (Throwable e) {
	        // 異常終了時の処理
	        
	        System.out.println("画像置くのに失敗したよ");
	        System.out.println(e);
	    }
        return null;
	}
}
