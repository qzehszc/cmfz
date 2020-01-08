package com.baizhi.ww.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attention {

  @Id
  private String id;
  private String guruId;
  private String userId;



}
