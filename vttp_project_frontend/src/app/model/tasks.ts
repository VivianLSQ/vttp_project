export interface Tasks {

  id: number;
  content: String;
  dateAdded: Date;
  dueDate: Date;
  notes: String;
}

export interface TaskDetails{
  content: String;
  dueDate: Date; //string|number|
  id: number;
  isCompleted: boolean;
  priority: string;
  category: string;
}



