package ru.hogwardts.school.service;

import org.hibernate.mapping.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwardts.school.model.Avatar;
import ru.hogwardts.school.model.Student;
import ru.hogwardts.school.repository.AvatarRepository;
import ru.hogwardts.school.repository.FacultyRepository;
import ru.hogwardts.school.repository.StudentRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {

    @Value("${students.avatars.dir.path}")
    private String avatarsDir;
    private final StudentRepository studentRepository;

    private final AvatarRepository avatarRepository;
    private final FacultyRepository facultyRepository;

    private Logger logger = LoggerFactory.getLogger(AvatarService.class);

    public AvatarService(StudentRepository studentRepository, AvatarRepository avatarRepository,
                         FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
        this.facultyRepository = facultyRepository;
    }

    public Avatar findAvatar(Long id) {
        getLogger("findAvatar");
        return avatarRepository.findAvatarByStudentId(id);
    }

    public void uploadAvatar(Long studentId, MultipartFile file) throws IOException {
        getLogger("uploadAvatar");
        Student student = studentRepository.getById(studentId);
        Path filePath = Path.of(avatarsDir, student + "." + getExtensions(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);) {
            bis.transferTo(bos);
        }

        Avatar avatar = new Avatar();
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());
        avatar.setStudent(studentRepository.getById(studentId));
        avatarRepository.save(avatar);
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public List<Avatar> findAvatarsByPage(Integer page, Integer size) {
        getLogger("findAvatarsByPage");
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return avatarRepository.findAll(pageRequest).getContent();
    }
    private void getLogger(String methodName) {
        logger.debug("method called: " + methodName);
    }
}
