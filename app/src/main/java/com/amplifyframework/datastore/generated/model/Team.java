package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;
import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Team type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Teams")
public final class Team implements Model {
  public static final QueryField ID = field("Team", "id");
  public static final QueryField TEAM_TITLE = field("Team", "teamTitle");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String teamTitle;
  private final @ModelField(targetType="TaskModel") @HasMany(associatedWith = "teamID", type = TaskModel.class) List<TaskModel> TaskModels = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getTeamTitle() {
      return teamTitle;
  }
  
  public List<TaskModel> getTaskModels() {
      return TaskModels;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Team(String id, String teamTitle) {
    this.id = id;
    this.teamTitle = teamTitle;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Team team = (Team) obj;
      return ObjectsCompat.equals(getId(), team.getId()) &&
              ObjectsCompat.equals(getTeamTitle(), team.getTeamTitle()) &&
              ObjectsCompat.equals(getCreatedAt(), team.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), team.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTeamTitle())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Team {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("teamTitle=" + String.valueOf(getTeamTitle()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static TeamTitleStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Team justId(String id) {
    return new Team(
      id,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      teamTitle);
  }
  public interface TeamTitleStep {
    BuildStep teamTitle(String teamTitle);
  }
  

  public interface BuildStep {
    Team build();
    BuildStep id(String id);
  }
  

  public static class Builder implements TeamTitleStep, BuildStep {
    private String id;
    private String teamTitle;
    @Override
     public Team build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Team(
          id,
          teamTitle);
    }
    
    @Override
     public BuildStep teamTitle(String teamTitle) {
        Objects.requireNonNull(teamTitle);
        this.teamTitle = teamTitle;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String teamTitle) {
      super.id(id);
      super.teamTitle(teamTitle);
    }
    
    @Override
     public CopyOfBuilder teamTitle(String teamTitle) {
      return (CopyOfBuilder) super.teamTitle(teamTitle);
    }
  }
  
}
