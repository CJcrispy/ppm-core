package com.example.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "projectTasks")
public class ProjectTask {

    @Id
    @GeneratedValue
    private Long id;
    @Column(updatable = false, unique = true)
    private String projectSequence;
    @NotBlank(message = "Please include a project summary")
    private String summary;

    private String acceptanceCriteria;
    private String status;
    private Integer priority;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date dueDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date create_At;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date update_At;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="backlog_id", updatable = false, nullable = false)
    @JsonIgnore
    private Backlog backlog;

    @Column(updatable = false)
    private String projectIdentifier;

    @PrePersist
    protected void onCreate(){
        this.create_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.update_At = new Date();
    }
}
