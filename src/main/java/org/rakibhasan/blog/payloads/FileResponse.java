package org.rakibhasan.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FileResponse {
    private String fileName;
    //private String fileType;
    //private String fileSize;
    //private String filePath;
    private String message;
}
