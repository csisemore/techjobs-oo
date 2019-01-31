package org.launchcode.controllers;

// not in original import org.launchcode.models.Job;
import org.launchcode.models.Job;
import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "job")
public class JobController {

    private JobData jobData = JobData.getInstance();
    private Job newJob;

    // The detail display for a given Job at URLs like /job?id=17
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, int id) {

        // TODO #1 - get the Job with the given ID and pass it into the view
        JobData returnjob = JobData.getInstance();
        model.addAttribute("job", returnjob.findById(id));

        return "job-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new JobForm());
        return "new-job";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @Valid JobForm jobForm, Errors errors) {

        // TODO #6 - Validate the JobForm model, and if valid, create a
        //  new Job and add it to the jobData data store. Then
        //  redirect to the job detail view for the new Job.

        //private Job newJob();

        //not sure WTF is up, as this should work
        //String newJob = newJob();
        //private Job newJob  = newJob();
        //Job newJob();
        //String Job newJob;
        //private String Job newJob = newJob();
        // temp
        if (errors.hasErrors()) {
            return "new-job";
        }

        Job newJob = new Job();

        newJob.setName(jobForm.getName());
        newJob.setEmployer(jobData.getEmployers().findById(jobForm.getEmployerId()));
        newJob.setLocation(jobData.getLocations().findById(jobForm.getLocationId()));
        newJob.setCoreCompetency(jobData.getCoreCompetencies().findById(jobForm.getCoreCompetencyId()));
        newJob.setPositionType(jobData.getPositionTypes().findById(jobForm.getPositionTypeId()));
//        newJob.setCoreCompetency(jobData.getCoreCompetencies().findById(jobForm.getCoreCompetencyId()));

        //return "";
        jobData.add(newJob);
        // shouldn't be needed  model.addAttribute("job", newJob);
        return "redirect:?id=" + newJob.getId();

    }

//    private Job newJob() {
//    }

//    private String newJob() {
//        return "redirect:?id=" + (newJob.getId());
//    }

//    private String newJob() {
//        return "" ;
//    }


}
